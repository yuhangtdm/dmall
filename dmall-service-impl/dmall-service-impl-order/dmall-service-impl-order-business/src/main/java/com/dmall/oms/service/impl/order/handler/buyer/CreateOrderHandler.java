package com.dmall.oms.service.impl.order.handler.buyer;

import com.dmall.common.enums.YNEnum;
import com.dmall.oms.service.support.SyncEsOrderSupport;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.cart.api.dto.delete.DeleteCartRequestDTO;
import com.dmall.cart.api.dto.list.CartListResponseDTO;
import com.dmall.common.constants.MqConstants;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.BasicStatusEnum;
import com.dmall.common.enums.RocketMQDelayLevelEnum;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.IdGeneratorUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.cache.redis.lock.DistributedLockService;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.enums.InvoiceTypeEnum;
import com.dmall.oms.api.dto.createorder.CreateOrderRequestDTO;
import com.dmall.oms.api.dto.createorder.OrderAddressRequestDTO;
import com.dmall.oms.api.dto.createorder.OrderInvoiceRequestDTO;
import com.dmall.oms.api.dto.createorder.OrderSkuRequestDTO;
import com.dmall.oms.api.enums.*;
import com.dmall.oms.feign.CartFeign;
import com.dmall.oms.feign.SkuFeign;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.mapper.OrderItemMapper;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.service.impl.order.OrderConstants;
import com.dmall.oms.service.support.OrderLogSupport;
import com.dmall.oms.service.support.OrderStatusSupport;
import com.dmall.pay.api.enums.PaymentStatusEnum;
import com.dmall.pms.api.dto.sku.request.CheckCreateOrderRequestDTO;
import com.dmall.pms.api.dto.sku.request.CheckOrderSkuRequestDTO;
import com.dmall.pms.api.dto.sku.request.SkuStockRequestDTO;
import com.dmall.pms.api.dto.sku.request.StockRequestDTO;
import com.dmall.pms.api.dto.sku.response.get.BasicSkuResponseDTO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQLocalRequestCallback;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 创建订单处理器
 * @author: created by hang.yu on 2020/3/28 15:53
 */
@Slf4j
@Component
public class CreateOrderHandler extends AbstractCommonHandler<CreateOrderRequestDTO, OrderDO, String> {

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private CartFeign cartFeign;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private DistributedLockService distributedLockService;

    @Autowired
    private OrderStatusSupport orderStatusSupport;

    @Autowired
    private OrderLogSupport orderLogSupport;

    @Autowired
    private SyncEsOrderSupport syncEsOrderSupport;

    @Override
    public BaseResult<Long> processor(CreateOrderRequestDTO requestDTO) {
        // step1. 获取登录的会员信息
        PortalMemberDTO loginMember = PortalMemberContextHolder.get();
        String checkKey = StrUtil.format(OrderConstants.CHECK_ORDER_KEY, loginMember.getId());
        // 校验3秒钟之内不能重复提交订单
        if (!distributedLockService.getLock(checkKey, requestDTO.getOrderKey(), 3)) {
            return ResultUtil.fail(OmsErrorEnum.SUBMIT_REPEAT);
        }
        // 校验订单提交成功之后不能重复下单
        String key = StrUtil.format(OrderConstants.ORDER_KEY, loginMember.getId());
        if (redisTemplate.opsForValue().get(key) == null) {
            return ResultUtil.fail(OmsErrorEnum.SUBMIT_REPEAT);
        }
        List<Long> skuIds = requestDTO.getOrderSku().stream().map(OrderSkuRequestDTO::getSkuId).collect(Collectors.toList());
        Map<Long, OrderSkuRequestDTO> skuMap = requestDTO.getOrderSku().stream()
                .collect(Collectors.toMap(OrderSkuRequestDTO::getSkuId, orderSkuRequestDTO -> orderSkuRequestDTO));
        // step2. 调用sku校验接口
        BaseResult<List<BasicSkuResponseDTO>> skuResponse = skuFeign.createOrderCheck(buildCheckOrderRequest(requestDTO));
        if (!skuResponse.getResult()) {
            return ResultUtil.fail(skuResponse.getCode(), skuResponse.getMsg());
        }
        // step3. 写入订单表
        OrderDO orderDO = insertOrderDO(requestDTO);
        // step4. 写入订单详情表
        insertOrderItem(skuMap, skuResponse, orderDO);
        // step5. 写入订单状态记录表
        orderStatusSupport.insert(orderDO.getId(), OrderStatusEnum.WAIT_PAY.getCode());
        // step6. 写入订单日志表
        orderLogSupport.insert(orderDO.getId(), OrderOperateEnum.CREATE, false);
        // step7. 调用删除商品购物车的接口
        BaseResult<CartListResponseDTO> deleteCart = cartFeign.delete(new DeleteCartRequestDTO().setSkuIds(skuIds));
        if (!deleteCart.getResult()) {
            throw new BusinessException(BasicStatusEnum.FAIL);
        }
        // step8.调用锁定库存接口
        BaseResult<Void> lockStock = skuFeign.lockStock(buildLockStockRequest(requestDTO));
        if (!lockStock.getResult()) {
            throw new BusinessException(BasicStatusEnum.FAIL);
        }
        // step9. 发送延迟消息到mq 一小时后取消未支付的订单
        sendDelayMq(orderDO);
        // step10. 写入es
        syncEsOrderSupport.sendOrderEsMq(orderDO.getId());
        // step11. 释放锁
        distributedLockService.releaseLock(checkKey, requestDTO.getOrderKey());
        redisTemplate.delete(key);
        return ResultUtil.success(orderDO.getId());
    }


    /**
     * 插入主订单表
     */
    private OrderDO insertOrderDO(CreateOrderRequestDTO requestDTO) {
        OrderDO orderDO = new OrderDO();
        orderDO.setId(IdGeneratorUtil.snowflakeId());
        orderDO.setStatus(OrderStatusEnum.WAIT_PAY.getCode());
        orderDO.setPaymentStatus(PaymentStatusEnum.WAIT_PAY.getCode());
        orderDO.setDeliverStatus(OrderDeliverStatusEnum.WAIT.getCode());
        orderDO.setCommentStatus(OrderCommentStatusEnum.NO.getCode());
        orderDO.setReceiveStatus(OrderReceiveStatusEnum.WAIT.getCode());
        orderDO.setSource(requestDTO.getSource());
        int totalNumber = requestDTO.getOrderSku().stream().mapToInt(OrderSkuRequestDTO::getNumber).sum();
        orderDO.setSkuCount(totalNumber);
        // 价格相关
        orderDO.setTotalSkuPrice(requestDTO.getTotalSkuPrice());
        orderDO.setOrderPrice(requestDTO.getOrderPrice());
        orderDO.setPaymentPrice(requestDTO.getOrderPrice());
        orderDO.setFreightPrice(requestDTO.getFreightPrice());
        orderDO.setRemark(requestDTO.getRemark());

        // 收货地址相关
        OrderAddressRequestDTO address = requestDTO.getOrderAddressRequestDTO();
        orderDO.setReceiverId(address.getId());
        orderDO.setReceiverName(address.getName());
        orderDO.setReceiverPhone(address.getPhone());
        orderDO.setReceiverProvince(address.getProvince());
        orderDO.setReceiverCity(address.getCity());
        orderDO.setReceiverRegion(address.getRegion());
        orderDO.setReceiverDetailAddress(address.getDetailAddress());

        // 发票相关
        orderDO.setInvoiceType(InvoiceTypeEnum.ELECTRONICS.getCode());
        OrderInvoiceRequestDTO invoice = requestDTO.getOrderInvoiceRequestDTO();
        orderDO.setInvoiceHeader(invoice.getInvoiceHeader());
        orderDO.setInvoiceContent(invoice.getInvoiceContent());
        orderDO.setPersonalName(invoice.getPersonalName());
        orderDO.setCompanyName(invoice.getCompanyName());
        orderDO.setCustomerTaxNumber(invoice.getCustomerTaxNumber());
        orderDO.setInvoiceReceiverPhone(invoice.getReceiverPhone());
        orderDO.setInvoiceReceiverEmail(invoice.getReceiverEmail());
        // 默认未拆分
        orderDO.setSplit(SplitEnum.NOT.getCode());
        orderMapper.insert(orderDO);
        return orderDO;
    }

    /**
     * 插入订单项表
     */
    private void insertOrderItem(Map<Long, OrderSkuRequestDTO> skuMap, BaseResult<List<BasicSkuResponseDTO>> skuResponse, OrderDO orderDO) {
        for (BasicSkuResponseDTO sku : skuResponse.getData()) {
            OrderItemDO orderItemDO = new OrderItemDO();
            orderItemDO.setOrderId(orderDO.getId());
            orderItemDO.setMerchantsId("");
            orderItemDO.setProductId(sku.getProductId());
            orderItemDO.setSkuId(sku.getId());
            orderItemDO.setSkuName(sku.getName());
            orderItemDO.setSkuPic(sku.getPic());
            orderItemDO.setSkuTotalPrice(sku.getPrice());
            orderItemDO.setSkuNumber(skuMap.get(sku.getId()).getNumber());
            orderItemDO.setSkuTotalPrice(NumberUtil.mul(sku.getPrice(), orderItemDO.getSkuNumber()));
            orderItemDO.setSkuSpecifications(sku.getSkuSpecificationsJson());
            orderItemDO.setRealPrice(orderItemDO.getSkuTotalPrice());
            orderItemDO.setCommentStatus(YNEnum.N.getCode());
            orderItemMapper.insert(orderItemDO);
        }
    }

    /**
     * 发送延迟消息到mq 一小时后取消未支付的订单
     */
    private void sendDelayMq(OrderDO orderDO) {
        rocketMQTemplate.sendAndReceive(MqConstants.DELAY_CANCEL_ORDER_TOPIC, orderDO.getId(),
                new RocketMQLocalRequestCallback() {
                    @Override
                    public void onSuccess(Object message) {
                        log.info("send mq success{}", message);
                    }

                    @SneakyThrows
                    @Override
                    public void onException(Throwable e) {
                        log.error("send mq error", e);
                        throw new BusinessException(BasicStatusEnum.FAIL);
                    }
                }, 1000, RocketMQDelayLevelEnum.SEVENTEEN.getCode());
    }


    /**
     * 构建校验订单请求实体
     */
    private CheckCreateOrderRequestDTO buildCheckOrderRequest(CreateOrderRequestDTO requestDTO) {
        CheckCreateOrderRequestDTO checkCreateOrderRequestDTO = new CheckCreateOrderRequestDTO();
        List<CheckOrderSkuRequestDTO> checkOrderList = requestDTO.getOrderSku().stream()
                .map(orderSkuRequestDTO -> {
                    CheckOrderSkuRequestDTO checkOrderSkuRequestDTO = new CheckOrderSkuRequestDTO();
                    checkOrderSkuRequestDTO.setSkuId(orderSkuRequestDTO.getSkuId());
                    checkOrderSkuRequestDTO.setCount(orderSkuRequestDTO.getNumber());
                    checkOrderSkuRequestDTO.setSkuPrice(orderSkuRequestDTO.getSkuPrice());
                    return checkOrderSkuRequestDTO;
                }).collect(Collectors.toList());
        checkCreateOrderRequestDTO.setOrderSku(checkOrderList);
        checkCreateOrderRequestDTO.setTotalSkuPrice(requestDTO.getTotalSkuPrice());
        checkCreateOrderRequestDTO.setFreightPrice(requestDTO.getFreightPrice());
        checkCreateOrderRequestDTO.setOrderPrice(requestDTO.getOrderPrice());
        return checkCreateOrderRequestDTO;
    }

    /**
     * 生成锁定库存请求参数
     */
    private StockRequestDTO buildLockStockRequest(CreateOrderRequestDTO requestDTO) {
        StockRequestDTO stockRequestDTO = new StockRequestDTO();
        List<SkuStockRequestDTO> lockSkuList = requestDTO.getOrderSku().stream().map(orderSkuRequestDTO -> {
            SkuStockRequestDTO skuStockRequestDTO = new SkuStockRequestDTO();
            skuStockRequestDTO.setSkuId(orderSkuRequestDTO.getSkuId());
            skuStockRequestDTO.setNumber(orderSkuRequestDTO.getNumber());
            return skuStockRequestDTO;
        }).collect(Collectors.toList());
        stockRequestDTO.setSku(lockSkuList);

        return stockRequestDTO;
    }
}
