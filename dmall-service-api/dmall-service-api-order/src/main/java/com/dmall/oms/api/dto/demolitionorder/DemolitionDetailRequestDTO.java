package com.dmall.oms.api.dto.demolitionorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description: 拆单细节请求实体
 * @author: created by hang.yu on 2020/4/5 9:57
 */
@Data
@ApiModel(value = "DemolitionOrderRequestDTO", description = "拆单细节请求实体")
public class DemolitionDetailRequestDTO {

    @ApiModelProperty(value = "订单项id", position = 1)
    private Long orderItemId;

    @ApiModelProperty(value = "仓库id", position = 2)
    @NotNull(message = "仓库id不能为空")
    private Long warehouseId;

    @ApiModelProperty(value = "发货人id", position = 3)
    @NotNull(message = "发货人id不能为空")
    private Long  deliveryId;
}
