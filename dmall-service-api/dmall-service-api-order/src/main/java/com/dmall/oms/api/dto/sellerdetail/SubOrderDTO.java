package com.dmall.oms.api.dto.sellerdetail;

import com.dmall.oms.api.enums.SubOrderStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 子订单详情实体
 * @author: created by hang.yu on 2020/4/6 11:33
 */
@Data
@ApiModel(value = "SubOrderDTO", description = "子订单详情实体")
public class SubOrderDTO {

    @ApiModelProperty(value = "子订单id", position = 1)
    private Long subOrderId;

    @ApiModelProperty(value = "订单明细信息", position = 2)
    private List<OrderItemDTO> orderItems;

    @ApiModelProperty(value = "子订单状态", position = 3)
    private SubOrderStatusEnum subOrderStatusEnum;

    @ApiModelProperty(value = "仓库id", position = 4)
    private Long warehouseId;

    @ApiModelProperty(value = "物流信息", position = 5)
    private LogisticsDTO logistics;

    @ApiModelProperty(value = "物流信息", position = 6)
    private DeliverDTO deliver;
}
