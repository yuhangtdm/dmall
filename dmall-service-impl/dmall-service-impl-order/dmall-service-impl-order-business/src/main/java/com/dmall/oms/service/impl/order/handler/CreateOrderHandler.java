package com.dmall.oms.service.impl.order.handler;

import cn.hutool.core.util.NumberUtil;
import com.dmall.cart.api.dto.delete.DeleteCartRequestDTO;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.BasicStatusEnum;
import com.dmall.common.enums.RocketMQDelayLevelEnum;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.IdGeneratorUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.createorder.CreateOrderRequestDTO;
import com.dmall.oms.api.dto.createorder.OrderAddressRequestDTO;
import com.dmall.oms.api.dto.createorder.OrderInvoiceRequestDTO;
import com.dmall.oms.api.dto.createorder.OrderSkuRequestDTO;
import com.dmall.oms.api.enums.CreateOrderErrorEnum;
import com.dmall.oms.api.enums.OrderStatusEnum;
import com.dmall.oms.feign.CartFeign;
import com.dmall.oms.feign.SkuFeign;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.dataobject.OrderStatusDO;
import com.dmall.oms.generator.mapper.OrderItemMapper;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.generator.mapper.OrderStatusMapper;
import com.dmall.oms.service.impl.order.FreightMoneyUtil;
import com.dmall.pms.api.dto.sku.response.get.BasicSkuResponseDTO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQLocalRequestCallback;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
    public BaseResult<String> processor(CreateOrderRequestDTO requestDTO) {
        List<Long> skuIds = requestDTO.getOrderSku().stream().map(OrderSkuRequestDTO::getSkuId).collect(Collectors.toList());
        Map<Long, OrderSkuRequestDTO> skuMap = requestDTO.getOrderSku().stream()
                .collect(Collectors.toMap(OrderSkuRequestDTO::getSkuId, orderSkuRequestDTO -> orderSkuRequestDTO));

        // step1. 调用sku接口获取sku信息
        BaseResult<List<BasicSkuResponseDTO>> skuResponse = skuFeign.getBasic(skuIds);
        if (!skuResponse.getResult()) {
            return ResultUtil.fail(BasicStatusEnum.FAIL);
        }

        List<BasicSkuResponseDTO> skuData = skuResponse.getData();

        //  step2. 验证价格
        BigDecimal skuTotalPrice = BigDecimal.ZERO;
        for (BasicSkuResponseDTO sku : skuData) {
            if (!NumberUtil.equals(skuMap.get(sku.getId()).getSkuPrice(), sku.getPrice())) {
                return ResultUtil.fail(CreateOrderErrorEnum.SKU_PRICE_CHANGE);
            }
            skuTotalPrice = skuTotalPrice.add(sku.getPrice());
        }
        if (!NumberUtil.equals(skuTotalPrice, requestDTO.getTotalSkuMoney())) {
            return ResultUtil.fail(CreateOrderErrorEnum.SKU_TOTAL_MONEY_CHANGE);
        }
        BigDecimal freightMoney = FreightMoneyUtil.getFreightMoney(skuTotalPrice);
        if (!NumberUtil.equals(freightMoney, requestDTO.getFreightMoney())) {
            return ResultUtil.fail(CreateOrderErrorEnum.FREIGHT_CHANGE);
        }
        if (!NumberUtil.equals(NumberUtil.add(skuTotalPrice, freightMoney), requestDTO.getOrderMoney())) {
            return ResultUtil.fail(CreateOrderErrorEnum.ORDER_MONEY_CHANGE);
        }

        // step3. 验证库存
        for (BasicSkuResponseDTO sku : skuData) {
            int availableStock = sku.getStock() - sku.getLockStock();
            if (skuMap.get(sku.getId()).getCount() > availableStock) {
                return ResultUtil.fail(CreateOrderErrorEnum.INSUFFICIENT_INVENTORY);
            }
        }

        // 获取登录的会员信息
        PortalMemberDTO loginMember = PortalMemberContextHolder.get();

        // step4. 写入订单表
        OrderDO orderDO = new OrderDO();
        orderDO.setId(IdGeneratorUtil.snowflakeId());
        orderDO.setStatus(OrderStatusEnum.WAIT_PAY.getCode());
        orderDO.setSource(requestDTO.getSource());
        orderDO.setMemberPhone(loginMember.getPhone());
        int totalNumber = requestDTO.getOrderSku().stream().mapToInt(OrderSkuRequestDTO::getCount).sum();
        orderDO.setSkuCount(totalNumber);
        orderDO.setTotalSkuAmount(skuTotalPrice);
        orderDO.setOrderAmount(requestDTO.getOrderMoney());
        // 一期不做优惠相关 实际支付金额 = 订单总金额
        orderDO.setPayAmount(requestDTO.getOrderMoney());
        orderDO.setFreightAmount(freightMoney);
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

        // step5. 写入订单详情表
        for (BasicSkuResponseDTO sku : skuData) {
            OrderItemDO orderItemDO = new OrderItemDO();
            orderItemDO.setOrderId(orderDO.getId());
            orderItemDO.setMerchantsId("");
            orderItemDO.setProductId(sku.getProductId());
            orderItemDO.setSkuId(sku.getId());
            orderItemDO.setSkuName(sku.getName());
            orderItemDO.setSkuPic(sku.getPic());
            orderItemDO.setSkuPrice(sku.getPrice());
            orderItemDO.setSkuNumber(skuMap.get(sku.getId()).getCount());
            orderItemDO.setSkuAmount(NumberUtil.mul(sku.getPrice(), orderItemDO.getSkuNumber()));
            orderItemDO.setSkuSpecifications(sku.getSkuSpecificationsJson());
            orderItemDO.setRealAmount(orderItemDO.getSkuAmount());
            orderItemMapper.insert(orderItemDO);
        }

        // step6. 写入订单状态记录表
        OrderStatusDO orderStatusDO = new OrderStatusDO();
        orderStatusDO.setOrderId(orderDO.getId());
        orderStatusDO.setOrderStatus(OrderStatusEnum.WAIT_PAY.getCode());
        orderStatusMapper.insert(orderStatusDO);

        // step7. 调用删除商品购物车的接口
        DeleteCartRequestDTO deleteCartRequestDTO = new DeleteCartRequestDTO();
        deleteCartRequestDTO.setSkuIds(skuIds);
        cartFeign.delete(deleteCartRequestDTO);

        // step8. 发送延迟消息到mq 一小时后取消未支付的订单
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
                        throw e;
                    }
                }, 1000, RocketMQDelayLevelEnum.SEVENTEEN.getCode());

        // 调用锁定库存接口

        return null;
    }
}
