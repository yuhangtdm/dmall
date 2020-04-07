package com.dmall.oms.api.dto.sellerdetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 卖家端订单详情响应实体
 * @author: created by hang.yu on 2020/4/5 22:15
 */
@Data
@ApiModel(value = "SellerOrderDetailResponseDTO", description = "卖家端订单详情响应实体")
public class SellerOrderDetailResponseDTO {

    @ApiModelProperty(value = "订单基础信息", position = 1)
    private OrderBasicDTO basicOrder;

    @ApiModelProperty(value = "收货人信息", position = 2)
    private SellerReceiverDTO receiver;

    @ApiModelProperty(value = "发票信息", position = 3)
    private SellerInvoiceDTO invoice;

    @ApiModelProperty(value = "拆单信息", position = 4)
    private SplitOrderDTO splitOrder;

    @ApiModelProperty(value = "订单项列表", position = 5)
    private List<OrderItemDTO> items;

    @ApiModelProperty(value = "订单状态列表", position = 6)
    private List<SellerOrderStatusDTO> orderStatusList;

    @ApiModelProperty(value = "订单日志列表", position = 7)
    private List<OrderLogDTO> orderLogList;

    @ApiModelProperty(value = "订单支付列表", position = 8)
    private List<PaymentDTO> paymentList;
}
