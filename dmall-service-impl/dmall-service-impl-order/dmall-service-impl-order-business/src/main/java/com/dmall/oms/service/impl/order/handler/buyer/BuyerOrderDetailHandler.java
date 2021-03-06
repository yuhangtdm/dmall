package com.dmall.oms.service.impl.order.handler.buyer;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.enums.InvoiceContentEnum;
import com.dmall.mms.api.enums.InvoiceHeaderEnum;
import com.dmall.oms.api.dto.buyerdetail.*;
import com.dmall.oms.api.dto.common.BuyerOrderItemDTO;
import com.dmall.oms.api.enums.OrderStatusEnum;
import com.dmall.oms.api.enums.SplitEnum;
import com.dmall.oms.generator.dataobject.OrderDO;
import com.dmall.oms.generator.dataobject.OrderItemDO;
import com.dmall.oms.generator.dataobject.OrderStatusDO;
import com.dmall.oms.generator.dataobject.SubOrderDO;
import com.dmall.oms.service.support.AfterSaleSupport;
import com.dmall.oms.service.support.OrderItemSupport;
import com.dmall.oms.service.support.OrderStatusSupport;
import com.dmall.oms.service.validate.OmsValidate;
import com.dmall.pay.api.enums.PaymentTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 买家端订单详情处理器
 * @author: created by hang.yu on 2020/4/6 10:41
 */
@Component
public class BuyerOrderDetailHandler extends AbstractCommonHandler<Long, OrderDO, BuyerOrderDetailResponseDTO> {

    @Autowired
    private OrderStatusSupport orderStatusSupport;

    @Autowired
    private AfterSaleSupport afterSaleSupport;

    @Autowired
    private OrderItemSupport orderItemSupport;

    @Autowired
    private OmsValidate omsValidate;

    @Override
    public BaseResult<BuyerOrderDetailResponseDTO> processor(Long subOrderId) {
        SubOrderDO subOrderDO = omsValidate.validateSubOrder(subOrderId);
        OrderDO orderDO = omsValidate.validateOrder(subOrderDO.getOrderId());
        omsValidate.authentication(orderDO.getCreator());
        BuyerOrderDetailResponseDTO responseDTO = new BuyerOrderDetailResponseDTO();
        responseDTO.setOrderId(orderDO.getId());
        responseDTO.setSubOrderId(subOrderId);
        responseDTO.setReceiver(buildReceiver(orderDO));
        responseDTO.setDeliver(buildDeliver(subOrderDO));
        responseDTO.setPayment(buildPayment(orderDO));
        responseDTO.setInvoiceDTO(buildInvoice(orderDO));
        responseDTO.setOrderStatusList(buildOrderStatusList(orderDO.getId()));
        responseDTO.setSkuList(buildSkuList(orderDO, subOrderId));

        return ResultUtil.success(responseDTO);
    }

    private List<BuyerOrderItemDTO> buildSkuList(OrderDO orderDO, Long subOrderId) {
        if (SplitEnum.NOT_NEED.getCode().equals(orderDO.getSplit())) {
            // 无需拆单
            return orderItemSupport.listByOrderId(orderDO.getId()).stream().map(this::getOrderItemDTO)
                .collect(Collectors.toList());
        } else {
            // 已拆成多单
            return orderItemSupport.listBySubOrderId(subOrderId).stream().map(this::getOrderItemDTO)
                .collect(Collectors.toList());
        }

    }

    private BuyerOrderItemDTO getOrderItemDTO(OrderItemDO orderItemDO) {
        BuyerOrderItemDTO orderItem = new BuyerOrderItemDTO();
        orderItem.setSkuId(orderItemDO.getSkuId());
        orderItem.setSkuName(orderItemDO.getSkuName());
        orderItem.setSkuPrice(orderItemDO.getSkuTotalPrice());
        orderItem.setSkuNumber(orderItemDO.getSkuNumber());
        orderItem.setAfterSaleList(afterSaleSupport.buildAfterSaleListByOrderItemId(orderItemDO.getId()));
        return orderItem;
    }

    private ReceiverDTO buildReceiver(OrderDO orderDO) {
        ReceiverDTO receiverDTO = new ReceiverDTO();
        receiverDTO.setName(orderDO.getReceiverName());
        receiverDTO.setPhone(orderDO.getReceiverPhone());
        receiverDTO.setAddress(StrUtil.format("{}{}{}{}",
            orderDO.getReceiverProvince(), orderDO.getReceiverCity(),
            orderDO.getReceiverRegion(), orderDO.getReceiverDetailAddress()));
        return receiverDTO;
    }

    private DeliverDTO buildDeliver(SubOrderDO subOrderDO) {
        DeliverDTO deliverDTO = new DeliverDTO();
        deliverDTO.setLogisticsNo(subOrderDO.getLogisticsNo());
        deliverDTO.setLogisticsCompany(subOrderDO.getLogisticsCompany());
        return deliverDTO;
    }

    private PaymentDTO buildPayment(OrderDO orderDO) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentType(EnumUtil.getCodeDescEnum(PaymentTypeEnum.class, orderDO.getPaymentType()));
        paymentDTO.setPaymentDate(orderDO.getPaymentTime());
        paymentDTO.setTotalSkuPrice(orderDO.getTotalSkuPrice());
        paymentDTO.setPaymentPrice(orderDO.getPaymentPrice());
        paymentDTO.setCouponPrice(orderDO.getCouponPrice());
        paymentDTO.setFreightPrice(orderDO.getFreightPrice());
        return paymentDTO;
    }

    private InvoiceDTO buildInvoice(OrderDO orderDO) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        if (InvoiceHeaderEnum.COMPANY.getCode().equals(orderDO.getInvoiceHeader())) {
            invoiceDTO.setInvoiceHeader(orderDO.getCompanyName());
        } else {
            invoiceDTO.setInvoiceHeader(orderDO.getPersonalName());
        }
        invoiceDTO.setInvoiceHeaderType(orderDO.getInvoiceHeader());
        invoiceDTO.setCustomerTaxNumber(orderDO.getCustomerTaxNumber());
        invoiceDTO.setInvoiceContent(EnumUtil.getDesc(InvoiceContentEnum.class, orderDO.getInvoiceContent()));
        invoiceDTO.setInvoiceNumber(orderDO.getInvoiceNumber());
        return invoiceDTO;
    }

    private List<OrderStatusDTO> buildOrderStatusList(Long orderId) {
        List<OrderStatusDO> orderStatusList = orderStatusSupport.listByOrderId(orderId);
        return orderStatusList.stream().map(orderStatusDO -> {
            OrderStatusDTO orderStatusDTO = new OrderStatusDTO();
            orderStatusDTO
                .setOrderStatus(EnumUtil.getCodeDescEnum(OrderStatusEnum.class, orderStatusDO.getOrderStatus()));
            orderStatusDTO.setCreateTime(orderStatusDO.getGmtCreated());
            return orderStatusDTO;
        }).collect(Collectors.toList());
    }

}
