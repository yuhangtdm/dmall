package com.dmall.oms.api.dto.buyerorderpage.response;

import com.dmall.oms.api.enums.OrderStatusEnum;
import com.dmall.oms.api.enums.SplitEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @description: 买家端订单分页响应实体
 * @author: created by hang.yu on 2020/4/9 22:20
 */
@Data
@ApiModel(value = "BuyerOrderPageResponseDTO", description = "买家端订单分页响应实体")
public class BuyerOrderPageResponseDTO {

    @ApiModelProperty(value = "订单号", position = 1)
    private Long orderId;

    @ApiModelProperty(value = "订单状态", position = 2)
    private OrderStatusEnum orderStatus;

    @ApiModelProperty(value = "下单时间", position = 3)
    private Date orderTime;

    @ApiModelProperty(value = "拆单情况", position = 4)
    private SplitEnum split;

    @ApiModelProperty(value = "子订单列表", position = 5)
    private List<BuyerSubOrderDTO> subOrderList;

    @ApiModelProperty(value = "订单的sku列表", position = 6)
    private List<BuyerSkuDTO> skuList;

    @ApiModelProperty(value = "收货人信息", position = 7)
    private BuyerReceiverDTO receiver;

}
