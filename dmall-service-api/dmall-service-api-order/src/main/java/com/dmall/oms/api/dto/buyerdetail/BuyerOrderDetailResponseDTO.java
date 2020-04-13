package com.dmall.oms.api.dto.buyerdetail;

import com.dmall.oms.api.dto.common.BuyerOrderItemDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 买家端订单详情响应实体
 * @author: created by hang.yu on 2020/4/5 22:15
 */
@Data
@ApiModel(value = "BuyerOrderDetailResponseDTO", description = "买家端订单详情响应实体")
public class BuyerOrderDetailResponseDTO {

    @ApiModelProperty(value = "订单号", position = 1)
    private Long orderId;

    @ApiModelProperty(value = "子订单号", position = 2)
    private Long subOrderId;

    @ApiModelProperty(value = "收货人信息", position = 3)
    private ReceiverDTO receiver;

    @ApiModelProperty(value = "配送信息", position = 4)
    private DeliverDTO deliver;

    @ApiModelProperty(value = "付款信息", position = 5)
    private PaymentDTO payment;

    @ApiModelProperty(value = "发票信息", position = 6)
    private InvoiceDTO invoiceDTO;

    @ApiModelProperty(value = "订单状态列表", position = 7)
    private List<OrderStatusDTO> orderStatusList;

    @ApiModelProperty(value = "订单项列表", position = 8)
    private List<BuyerOrderItemDTO> skuList;
}
