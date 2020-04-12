package com.dmall.oms.api.dto.demolitionorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @description: 拆单细节请求实体
 * @author: created by hang.yu on 2020/4/5 9:57
 */
@Data
@ApiModel(value = "DemolitionOrderRequestDTO", description = "拆单细节请求实体")
public class DemolitionDetailRequestDTO {

    @ApiModelProperty(value = "订单项集合", position = 1)
    @NotNull(message = "订单项集合不能为空")
    @Size(min = 1, message = "订单项集合不能为空")
    private List<Long> orderItemIds;

    @ApiModelProperty(value = "仓库id", position = 2)
    @NotNull(message = "仓库id不能为空")
    private Long warehouseId;

    @ApiModelProperty(value = "发货人id", position = 3)
    @NotNull(message = "发货人id不能为空")
    private Long  deliveryId;
}
