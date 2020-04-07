package com.dmall.oms.service.impl.order.handler;

import com.dmall.oms.api.enums.OrderOperateEnum;

import java.util.Date;

import com.dmall.oms.api.enums.OrderStatusEnum;
import com.dmall.oms.api.dto.sellerdetail.SellerInvoiceDTO;
import com.dmall.oms.api.dto.sellerdetail.SellerReceiverDTO;
import com.dmall.oms.api.dto.sellerdetail.OrderBasicDTO;
import com.dmall.oms.api.dto.sellerdetail.SplitOrderDTO;

import java.math.BigDecimal;

import com.dmall.oms.api.dto.sellerdetail.OrderItemDTO;
import com.dmall.oms.api.enums.OrderDeliverStatusEnum;
import com.dmall.oms.api.dto.sellerdetail.DeliverDTO;
import com.dmall.oms.api.dto.sellerdetail.LogisticsDTO;
import com.dmall.oms.api.enums.SplitEnum;
import com.dmall.oms.api.enums.*;

import com.dmall.common.util.EnumUtil;
import com.dmall.oms.api.dto.sellerdetail.*;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.enums.SourceEnum;
import com.dmall.common.util.ResultUtil;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.service.impl.support.OrderLogSupport;
import com.dmall.oms.service.impl.support.SubOrderSupport;
import com.google.common.collect.Lists;

import com.dmall.common.dto.BaseResult;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.mapper.OrderItemMapper;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.generator.mapper.SubOrderMapper;
import com.dmall.oms.service.impl.support.OrderItemSupport;
import com.dmall.oms.service.impl.support.OrderStatusSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: created by hang.yu on 2020/4/7 22:27
 */
@Component
public class SellerOrderDetailHandler extends AbstractCommonHandler<Long, OrderDO, SellerOrderDetailResponseDTO> {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderStatusSupport orderStatusSupport;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderItemSupport orderItemSupport;

    @Autowired
    private SubOrderSupport subOrderSupport;

    @Autowired
    private OrderLogSupport orderLogSupport;

    @Override
    public BaseResult<SellerOrderDetailResponseDTO> processor(Long orderId) {
        SellerOrderDetailResponseDTO responseDTO = new SellerOrderDetailResponseDTO();
        OrderDO orderDO = orderMapper.selectById(orderId);
        if (orderDO == null) {
            return ResultUtil.fail(OrderErrorEnum.ORDER_NOT_EXISTS);
        }
        responseDTO.setBasicOrder(buildOrderBasic(orderDO));
        responseDTO.setReceiver(buildReceiver(orderDO));
        responseDTO.setInvoice(buildInvoice(orderDO));
        responseDTO.setSplitOrder(buildSplitOrder(orderDO));
        responseDTO.setItems(buildItems(orderId, orderDO.getSplit()));
        responseDTO.setOrderStatusList(buildOrderStatusList(orderId));
        responseDTO.setOrderLogList(buildOrderLogList(orderId));
        responseDTO.setPaymentList(buildPaymentList(orderId));
        return ResultUtil.success(responseDTO);
    }

    private List<PaymentDTO> buildPaymentList(Long orderId) {

        return null;
    }


    /**
     * 构建支付列表
     */
    private List<SellerOrderStatusDTO> buildOrderStatusList(Long orderId) {
        return orderStatusSupport.listByOrderId(orderId).stream()
                .map(orderStatusDO -> {
                    SellerOrderStatusDTO orderStatus = new SellerOrderStatusDTO();
                    orderStatus.setOrderStatus(EnumUtil.getCodeDescEnum(OrderStatusEnum.class, orderStatusDO.getOrderStatus()));
                    orderStatus.setCreateTime(orderStatusDO.getGmtCreated());
                    return orderStatus;
                }).collect(Collectors.toList());
    }

    /**
     * 构建日志列表
     */
    private List<OrderLogDTO> buildOrderLogList(Long orderId) {
        return orderLogSupport.listByOrderId(orderId).stream().map(orderLogDO -> {
            OrderLogDTO orderLog = new OrderLogDTO();
            orderLog.setOrderLogId(orderLogDO.getId());
            orderLog.setOrderId(orderLogDO.getOrderId());
            orderLog.setSubOrderId(orderLogDO.getSubOrderId());
            orderLog.setOperate(EnumUtil.getCodeDescEnum(OrderOperateEnum.class, orderLogDO.getOperate()));
            orderLog.setOperator(orderLogDO.getOperator());
            orderLog.setLogContent(orderLogDO.getLogContent());
            return orderLog;
        }).collect(Collectors.toList());
    }

    /**
     * 构建订单基础信息
     */
    private OrderBasicDTO buildOrderBasic(OrderDO orderDO) {
        OrderBasicDTO orderBasic = new OrderBasicDTO();
        orderBasic.setOrderId(orderDO.getId());
        orderBasic.setStatus(EnumUtil.getCodeDescEnum(OrderStatusEnum.class, orderDO.getStatus()));
        orderBasic.setPaymentStatus(EnumUtil.getCodeDescEnum(PaymentStatusEnum.class, orderDO.getPaymentStatus()));
        orderBasic.setDeliverStatus(EnumUtil.getCodeDescEnum(OrderDeliverStatusEnum.class, orderDO.getDeliverStatus()));
        orderBasic.setCommentStatus(EnumUtil.getCodeDescEnum(OrderCommentStatusEnum.class, orderDO.getCommentStatus()));
        orderBasic.setDeleteStatus(EnumUtil.getCodeDescEnum(YNEnum.class, orderDO.getIsDeleted()));
        orderBasic.setSource(EnumUtil.getCodeDescEnum(SourceEnum.class, orderDO.getSource()));
        orderBasic.setPaymentType(EnumUtil.getCodeDescEnum(PaymentTypeEnum.class, orderDO.getPaymentType()));
        orderBasic.setCancelType(EnumUtil.getCodeDescEnum(CancelTypeEnum.class, orderDO.getCancelType()));
        orderBasic.setSkuCount(orderDO.getSkuCount());
        orderBasic.setProductCount(orderDO.getProductCount());
        orderBasic.setTotalSkuAmount(orderDO.getTotalSkuAmount());
        orderBasic.setFreightAmount(orderDO.getFreightAmount());
        orderBasic.setOrderAmount(orderDO.getOrderAmount());
        orderBasic.setPaymentAmount(orderDO.getPaymentAmount());
        orderBasic.setDealAmount(orderDO.getDealAmount());
        orderBasic.setCouponAmount(orderDO.getCouponAmount());
        orderBasic.setRemark(orderDO.getRemark());
        orderBasic.setPaymentTime(orderDO.getPaymentTime());
        orderBasic.setReceiveTime(orderDO.getReceiveTime());
        orderBasic.setCancelTime(orderDO.getCancelTime());
        orderBasic.setDeleteTime(orderDO.getDeleteTime());
        orderBasic.setInvoiceTime(orderDO.getInvoiceTime());
        return orderBasic;
    }

    /**
     * 构建收货人信息
     */
    private SellerReceiverDTO buildReceiver(OrderDO orderDO) {
        SellerReceiverDTO sellerReceiver = new SellerReceiverDTO();
        sellerReceiver.setReceiverId(orderDO.getReceiverId());
        sellerReceiver.setReceiverName(orderDO.getReceiverName());
        sellerReceiver.setReceiverPhone(orderDO.getReceiverPhone());
        sellerReceiver.setReceiverProvince(orderDO.getReceiverProvince());
        sellerReceiver.setReceiverCity(orderDO.getReceiverCity());
        sellerReceiver.setReceiverRegion(orderDO.getReceiverRegion());
        sellerReceiver.setReceiverDetailAddress(orderDO.getReceiverDetailAddress());
        return sellerReceiver;
    }

    /**
     * 构建发票信息
     */
    private SellerInvoiceDTO buildInvoice(OrderDO orderDO) {
        SellerInvoiceDTO sellerInvoice = new SellerInvoiceDTO();
        sellerInvoice.setOpenInvoice(orderDO.getOpenInvoice());
        sellerInvoice.setInvoiceNumber(orderDO.getInvoiceNumber());
        sellerInvoice.setInvoiceType(orderDO.getInvoiceType());
        sellerInvoice.setInvoiceHeader(EnumUtil.getCodeDescEnum(InvoiceHeaderTypeEnum.class, orderDO.getInvoiceHeader()));
        sellerInvoice.setPersonalName(orderDO.getPersonalName());
        sellerInvoice.setInvoiceContent(EnumUtil.getCodeDescEnum(InvoiceContentEnum.class, orderDO.getInvoiceContent()));
        sellerInvoice.setCompanyName(orderDO.getCompanyName());
        sellerInvoice.setCustomerTaxNumber(orderDO.getCustomerTaxNumber());
        sellerInvoice.setInvoiceReceiverPhone(orderDO.getInvoiceReceiverPhone());
        sellerInvoice.setInvoiceReceiverEmail(orderDO.getInvoiceReceiverEmail());
        return sellerInvoice;
    }

    /**
     * 构建拆单信息
     */
    private SplitOrderDTO buildSplitOrder(OrderDO orderDO) {
        SplitOrderDTO splitOrder = new SplitOrderDTO();
        if (SplitEnum.IS.getCode().equals(orderDO.getSplit())) {
            splitOrder.setSplit(EnumUtil.getCodeDescEnum(SplitEnum.class, orderDO.getSplit()));
            splitOrder.setSplitReason(orderDO.getSplitReason());
            splitOrder.setSplitPerson(orderDO.getSplitPerson());
            splitOrder.setSubOrderList(buildSubOrderList(orderDO.getId()));
        }
        return splitOrder;
    }

    /**
     * 构建子订单信息
     */
    private List<SubOrderDTO> buildSubOrderList(Long orderId) {
        List<SubOrderDO> subOrderDOS = subOrderSupport.listByOrderId(orderId);
        return subOrderDOS.stream().map(subOrderDO -> {
            SubOrderDTO subOrder = new SubOrderDTO();
            subOrder.setSubOrderId(subOrderDO.getId());
            subOrder.setOrderItem(buildOrderItem(subOrderDO.getOrderItemId()));
            subOrder.setDeliverStatus(EnumUtil.getCodeDescEnum(OrderDeliverStatusEnum.class, subOrder.getDeliverStatus()));
            subOrder.setWarehouseId(subOrderDO.getWarehouseId());
            subOrder.setLogistics(buildLogistics(subOrderDO));
            subOrder.setDeliver(buildDeliver(subOrderDO));
            return subOrder;
        }).collect(Collectors.toList());
    }

    /**
     * 构建物流信息
     */
    private LogisticsDTO buildLogistics(SubOrderDO subOrderDO) {
        LogisticsDTO logistics = new LogisticsDTO();
        logistics.setLogisticsCompany(subOrderDO.getLogisticsCompany());
        logistics.setExpressFee(subOrderDO.getExpressFee());
        logistics.setLogisticsNo(subOrderDO.getLogisticsNo());
        return logistics;
    }

    /**
     * 构建发货人信息
     */
    private DeliverDTO buildDeliver(SubOrderDO subOrderDO) {
        DeliverDTO deliverDTO = new DeliverDTO();
        deliverDTO.setDeliverId(subOrderDO.getDeliverId());
        deliverDTO.setDeliverName(subOrderDO.getDeliverName());
        deliverDTO.setDeliverPhone(subOrderDO.getDeliverPhone());
        deliverDTO.setDeliverProvince(subOrderDO.getDeliverProvince());
        deliverDTO.setDeliverCity(subOrderDO.getDeliverCity());
        deliverDTO.setDeliverRegion(subOrderDO.getDeliverRegion());
        deliverDTO.setDeliverDetailAddress(subOrderDO.getDeliverDetailAddress());
        deliverDTO.setDeliverTime(subOrderDO.getDeliverTime());
        return deliverDTO;
    }

    /**
     * 构建订单项信息
     */
    private OrderItemDTO buildOrderItem(Long orderItemId) {
        OrderItemDTO orderItem = new OrderItemDTO();
        if (orderItemId == null) {
            return orderItem;
        }
        OrderItemDO orderItemDO = orderItemMapper.selectById(orderItemId);
        orderItem.setOrderItemId(orderItemId);
        orderItem.setSkuId(orderItemDO.getSkuId());
        orderItem.setProductId(orderItemDO.getProductId());
        orderItem.setSkuName(orderItemDO.getSkuName());
        orderItem.setSkuPic(orderItemDO.getSkuPic());
        orderItem.setSkuPrice(orderItemDO.getSkuPrice());
        orderItem.setSkuNumber(orderItemDO.getSkuNumber());
        orderItem.setSkuAmount(orderItemDO.getSkuAmount());
        orderItem.setSkuSpecifications(orderItemDO.getSkuSpecifications());
        orderItem.setCouponAmount(orderItemDO.getCouponAmount());
        orderItem.setRealAmount(orderItemDO.getRealAmount());
        return orderItem;
    }

    /**
     * 构建订单详情
     * 非已拆分的时候执行
     */
    private List<OrderItemDTO> buildItems(Long orderId, Integer split) {
        if (!SplitEnum.IS.getCode().equals(split)) {
            return Lists.newArrayList();
        }
        List<OrderItemDO> orderItemList = orderItemSupport.listByOrderId(orderId);
        return orderItemList.stream().map(orderItemDO -> buildOrderItem(orderItemDO.getOrderId())).collect(Collectors.toList());
    }
}
