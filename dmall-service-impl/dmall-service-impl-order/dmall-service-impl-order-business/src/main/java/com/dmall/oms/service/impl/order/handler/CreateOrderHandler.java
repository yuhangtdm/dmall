package com.dmall.oms.service.impl.order.handler;

import cn.hutool.core.util.NumberUtil;
import com.dmall.cart.api.dto.delete.DeleteCartRequestDTO;
import com.dmall.cart.api.dto.list.CartListResponseDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.BasicStatusEnum;
import com.dmall.common.enums.RocketMQDelayLevelEnum;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.IdGeneratorUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.createorder.CreateOrderRequestDTO;
import com.dmall.oms.api.dto.createorder.OrderAddressRequestDTO;
import com.dmall.oms.api.dto.createorder.OrderInvoiceRequestDTO;
import com.dmall.oms.api.dto.createorder.OrderSkuRequestDTO;
import com.dmall.oms.api.enums.OrderStatusEnum;
import com.dmall.oms.feign.CartFeign;
import com.dmall.oms.feign.SkuFeign;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.dataobject.OrderStatusDO;
import com.dmall.oms.generator.mapper.OrderItemMapper;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.generator.mapper.OrderStatusMapper;
import com.dmall.pms.api.dto.sku.request.CheckCreateOrderRequestDTO;
import com.dmall.pms.api.dto.sku.request.CheckOrderSkuRequestDTO;
import com.dmall.pms.api.dto.sku.request.LockSkuRequestDTO;
import com.dmall.pms.api.dto.sku.request.LockStockRequestDTO;
import com.dmall.pms.api.dto.sku.response.get.BasicSkuResponseDTO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQLocalRequestCallback;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private CartFeign cartFeign;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 取消订单的topic
     */
    private static final String CANCEL_ORDER_TOPIC = "cancelOrder";

    @Override
    public BaseResult<Long> processor(CreateOrderRequestDTO requestDTO) {
        List<Long> skuIds = requestDTO.getOrderSku().stream().map(OrderSkuRequestDTO::getSkuId).collect(Collectors.toList());
        Map<Long, OrderSkuRequestDTO> skuMap = requestDTO.getOrderSku().stream()
                .collect(Collectors.toMap(OrderSkuRequestDTO::getSkuId, orderSkuRequestDTO -> orderSkuRequestDTO));

        // step1. 调用sku校验接口
        BaseResult<List<BasicSkuResponseDTO>> skuResponse = skuFeign.createOrderCheck(buildCheckOrderRequest(requestDTO));
        if (!skuResponse.getResult()) {
            return ResultUtil.fail(skuResponse.getCode(), skuResponse.getMsg());
        }

        // step2. 获取登录的会员信息
        PortalMemberDTO loginMember = PortalMemberContextHolder.get();

        // step3. 写入订单表
        OrderDO orderDO = insertOrderDO(requestDTO, loginMember);

        // step4. 写入订单详情表
        insertOrderItem(skuMap, skuResponse, orderDO);

        // step5. 写入订单状态记录表
        insertOrderStatus(orderDO);

        // step6. 调用删除商品购物车的接口
        BaseResult<CartListResponseDTO> deleteCart = cartFeign.delete(new DeleteCartRequestDTO().setSkuIds(skuIds));
        if (!deleteCart.getResult()) {
            throw new BusinessException(BasicStatusEnum.FAIL);
        }

        // step7.调用锁定库存接口
        BaseResult<Void> lockStock = skuFeign.lockStock(buildLockStockRequest(requestDTO));
        if (!lockStock.getResult()) {
            throw new BusinessException(BasicStatusEnum.FAIL);
        }

        // step8. 发送延迟消息到mq 一小时后取消未支付的订单
        //todo  发送消息待优化
        sendDelayMq(orderDO);

        return ResultUtil.success(orderDO.getId());
    }

    /**
     * 插入主订单表
     */
    private OrderDO insertOrderDO(CreateOrderRequestDTO requestDTO, PortalMemberDTO loginMember) {
        OrderDO orderDO = new OrderDO();
        orderDO.setId(IdGeneratorUtil.snowflakeId());
        orderDO.setStatus(OrderStatusEnum.WAIT_PAY.getCode());
        orderDO.setSource(requestDTO.getSource());
        orderDO.setMemberPhone(loginMember.getPhone());
        int totalNumber = requestDTO.getOrderSku().stream().mapToInt(OrderSkuRequestDTO::getNumber).sum();
        orderDO.setSkuCount(totalNumber);
        orderDO.setTotalSkuAmount(requestDTO.getTotalSkuMoney());
        orderDO.setOrderAmount(requestDTO.getOrderMoney());
        // 一期不做优惠相关 实际支付金额 = 订单总金额
        orderDO.setPayAmount(requestDTO.getOrderMoney());
        orderDO.setFreightAmount(requestDTO.getFreightMoney());
        orderDO.setRemark(requestDTO.getRemark());
        OrderAddressRequestDTO address = requestDTO.getOrderAddressRequestDTO();
        orderDO.setReceiverName(address.getName());
        orderDO.setReceiverPhone(address.getPhone());
        orderDO.setReceiverProvince(address.getProvince());
        orderDO.setReceiverCity(address.getCity());
        orderDO.setReceiverRegion(address.getRegion());
        orderDO.setReceiverDetailAddress(address.getDetailAddress());
        orderDO.setInvoiceType(1);
        OrderInvoiceRequestDTO invoice = requestDTO.getOrderInvoiceRequestDTO();
        orderDO.setInvoiceHeader(invoice.getInvoiceHeader());
        orderDO.setInvoiceContent(invoice.getInvoiceContent());
        orderDO.setPersonalName(invoice.getPersonalName());
        orderDO.setCompanyName(invoice.getCompanyName());
        orderDO.setCustomerTaxNumber(invoice.getCustomerTaxNumber());
        orderDO.setInvoiceReceiverPhone(invoice.getReceiverPhone());
        orderDO.setInvoiceReceiverEmail(invoice.getReceiverEmail());
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
            orderItemDO.setSkuPrice(sku.getPrice());
            orderItemDO.setSkuNumber(skuMap.get(sku.getId()).getNumber());
            orderItemDO.setSkuAmount(NumberUtil.mul(sku.getPrice(), orderItemDO.getSkuNumber()));
            orderItemDO.setSkuSpecifications(sku.getSkuSpecificationsJson());
            orderItemDO.setRealAmount(orderItemDO.getSkuAmount());
            orderItemMapper.insert(orderItemDO);
        }
    }

    /**
     * 插入订单状态表
     */
    private void insertOrderStatus(OrderDO orderDO) {
        OrderStatusDO orderStatusDO = new OrderStatusDO();
        orderStatusDO.setOrderId(orderDO.getId());
        orderStatusDO.setOrderStatus(OrderStatusEnum.WAIT_PAY.getCode());
        orderStatusMapper.insert(orderStatusDO);
    }

    /**
     * 发送延迟消息到mq 一小时后取消未支付的订单
     */
    private void sendDelayMq(OrderDO orderDO) {
        rocketMQTemplate.sendAndReceive(CANCEL_ORDER_TOPIC, orderDO.getId(),
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
        checkCreateOrderRequestDTO.setTotalSkuMoney(requestDTO.getTotalSkuMoney());
        checkCreateOrderRequestDTO.setFreightMoney(requestDTO.getFreightMoney());
        checkCreateOrderRequestDTO.setOrderMoney(requestDTO.getOrderMoney());
        return checkCreateOrderRequestDTO;
    }

    /**
     * 生成锁定库存请求参数
     */
    private LockStockRequestDTO buildLockStockRequest(CreateOrderRequestDTO requestDTO) {
        LockStockRequestDTO lockStockRequestDTO = new LockStockRequestDTO();
        List<LockSkuRequestDTO> lockSkuList = requestDTO.getOrderSku().stream().map(orderSkuRequestDTO -> {
            LockSkuRequestDTO lockSkuRequestDTO = new LockSkuRequestDTO();
            lockSkuRequestDTO.setSkuId(orderSkuRequestDTO.getSkuId());
            lockSkuRequestDTO.setNumber(orderSkuRequestDTO.getNumber());
            return lockSkuRequestDTO;
        }).collect(Collectors.toList());
        lockStockRequestDTO.setSku(lockSkuList);

        return lockStockRequestDTO;
    }
}
