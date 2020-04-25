package com.dmall.oms.service.impl.order.handler.seller;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.SourceEnum;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.sellerdetail.*;
import com.dmall.oms.api.enums.*;
import com.dmall.oms.feign.PaymentFeign;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.generator.mapper.OrderMapper;
import com.dmall.oms.service.support.*;
import com.dmall.oms.service.validate.OmsValidate;
import com.dmall.pay.api.dto.listpayment.ListPaymentResponseDTO;
import com.dmall.pay.api.enums.PaymentStatusEnum;
import com.dmall.pay.api.enums.PaymentTypeEnum;
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
    private AfterSaleSupport afterSaleSupport;

    @Autowired
    private OrderItemSupport orderItemSupport;

    @Autowired
    private SubOrderSupport subOrderSupport;

    @Autowired
    private OrderLogSupport orderLogSupport;

    @Autowired
    private PaymentFeign payFeign;

    @Autowired
    private OmsValidate omsValidate;

    @Override
    public BaseResult<SellerOrderDetailResponseDTO> processor(Long orderId) {
        SellerOrderDetailResponseDTO responseDTO = new SellerOrderDetailResponseDTO();
        OrderDO orderDO = omsValidate.validateOrder(orderId);
        responseDTO.setBasicOrder(buildOrderBasic(orderDO));
        responseDTO.setReceiver(buildReceiver(orderDO));
        responseDTO.setInvoice(buildInvoice(orderDO));
        responseDTO.setSplitOrder(buildSplitOrder(orderDO));
        responseDTO.setOrderStatusList(buildOrderStatusList(orderId));
        responseDTO.setOrderLogList(buildOrderLogList(orderId));
        responseDTO.setPaymentList(buildPaymentList(orderId));
        return ResultUtil.success(responseDTO);
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
        orderBasic.setReceiveStatus(EnumUtil.getCodeDescEnum(OrderReceiveStatusEnum.class, orderDO.getReceiveStatus()));
        orderBasic.setCommentStatus(EnumUtil.getCodeDescEnum(OrderCommentStatusEnum.class, orderDO.getCommentStatus()));
        orderBasic.setDeleteStatus(EnumUtil.getCodeDescEnum(YNEnum.class, orderDO.getIsDeleted()));
        orderBasic.setSource(EnumUtil.getCodeDescEnum(SourceEnum.class, orderDO.getSource()));
        orderBasic.setPaymentType(EnumUtil.getCodeDescEnum(PaymentTypeEnum.class, orderDO.getPaymentType()));
        orderBasic.setCancelType(EnumUtil.getCodeDescEnum(CancelTypeEnum.class, orderDO.getCancelType()));
        orderBasic.setSkuCount(orderDO.getSkuCount());
        orderBasic.setProductCount(orderDO.getProductCount());
        orderBasic.setTotalSkuPrice(orderDO.getTotalSkuPrice());
        orderBasic.setFreightPrice(orderDO.getFreightPrice());
        orderBasic.setOrderPrice(orderDO.getOrderPrice());
        orderBasic.setPaymentPrice(orderDO.getPaymentPrice());
        orderBasic.setDealPrice(orderDO.getDealPrice());
        orderBasic.setCouponPrice(orderDO.getCouponPrice());
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
        splitOrder.setSplit(EnumUtil.getCodeDescEnum(SplitEnum.class, orderDO.getSplit()));
        splitOrder.setSplitReason(orderDO.getSplitReason());
        splitOrder.setSplitPerson(orderDO.getSplitPerson());
        splitOrder.setSubOrderList(buildSubOrderList(orderDO.getId()));
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
            subOrder.setOrderItems(buildOrderItem(subOrderDO.getId()));
            subOrder.setSubOrderStatusEnum(EnumUtil.getCodeDescEnum(SubOrderStatusEnum.class, subOrderDO.getStatus()));
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
    private List<OrderItemDTO> buildOrderItem(Long subOrderId) {
        List<OrderItemDO> orderItemList = orderItemSupport.listBySubOrderId(subOrderId);
        return orderItemList.stream().map(orderItemDO -> {
            OrderItemDTO orderItem = new OrderItemDTO();
            orderItem.setOrderItemId(orderItemDO.getId());
            orderItem.setSkuId(orderItemDO.getSkuId());
            orderItem.setProductId(orderItemDO.getProductId());
            orderItem.setSkuName(orderItemDO.getSkuName());
            orderItem.setSkuPic(orderItemDO.getSkuPic());
            orderItem.setSkuTotalPrice(orderItemDO.getSkuTotalPrice());
            orderItem.setSkuNumber(orderItemDO.getSkuNumber());
            orderItem.setSkuTotalPrice(orderItemDO.getSkuTotalPrice());
            orderItem.setSkuSpecifications(orderItemDO.getSkuSpecifications());
            orderItem.setCouponPrice(orderItemDO.getCouponPrice());
            orderItem.setRealPrice(orderItemDO.getRealPrice());
            orderItem.setAfterSaleList(afterSaleSupport.buildAfterSaleListByOrderItemId(orderItemDO.getId()));
            return orderItem;
        }).collect(Collectors.toList());
    }

    /**
     * 构建支付记录
     */
    private List<PaymentDTO> buildPaymentList(Long orderId) {
        BaseResult<List<ListPaymentResponseDTO>> paymentResponse = payFeign.listPayment(orderId);
        if (!paymentResponse.getResult()) {
            throw new BusinessException(paymentResponse.getCode(), paymentResponse.getMsg());
        }
        List<ListPaymentResponseDTO> data = paymentResponse.getData();
        return data.stream().map(listPaymentResponseDTO -> {
            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setPaymentId(listPaymentResponseDTO.getPaymentId());
            paymentDTO.setOrderId(listPaymentResponseDTO.getOrderId());
            paymentDTO.setPaymentType(listPaymentResponseDTO.getPaymentType());
            paymentDTO.setTradeNo(listPaymentResponseDTO.getTradeNo());
            paymentDTO.setAmount(listPaymentResponseDTO.getAmount());
            paymentDTO.setSubject(listPaymentResponseDTO.getSubject());
            paymentDTO.setPaymentStatus(listPaymentResponseDTO.getPaymentStatus());
            paymentDTO.setCallbackContent(listPaymentResponseDTO.getCallbackContent());
            paymentDTO.setCallBackTime(listPaymentResponseDTO.getCallBackTime());
            paymentDTO.setBuyerAliPayNo(listPaymentResponseDTO.getBuyerAliPayNo());
            paymentDTO.setSellerAliPayNo(listPaymentResponseDTO.getSellerAliPayNo());
            return paymentDTO;
        }).collect(Collectors.toList());
    }

}
