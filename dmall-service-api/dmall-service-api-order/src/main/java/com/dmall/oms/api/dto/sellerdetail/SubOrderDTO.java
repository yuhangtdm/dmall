package com.dmall.oms.api.dto.sellerdetail;

import com.dmall.oms.api.enums.OrderDeliverStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 子订单详情实体
 * @author: created by hang.yu on 2020/4/6 11:33
 */
@Data
@ApiModel(value = "SubOrderDTO", description = "子订单详情实体")
public class SubOrderDTO {

    @ApiModelProperty(value = "子订单id", position = 1)
    private Long subOrderId;

    @ApiModelProperty(value = "订单明细信息,无需拆分时为null", position = 2)
    private OrderItemDTO orderItem;

    @ApiModelProperty(value = "发货状态", position = 3)
    private OrderDeliverStatusEnum deliverStatus;

    @ApiModelProperty(value = "仓库id", position = 4)
    private Long warehouseId;

    @ApiModelProperty(value = "物流信息", position = 5)
    private LogisticsDTO logistics;

    @ApiModelProperty(value = "物流信息", position = 6)
    private DeliverDTO deliver;
}
