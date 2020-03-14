package com.dmall.oms.api.dto.cart.update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @description: 修改数量请求实体
 * @author: created by hang.yu on 2020/3/14 17:10
 */
@Data
@ApiModel(value = "UpdateNumberRequestDTO", description = "修改购物车数量请求实体")
public class UpdateNumberRequestDTO {

    @ApiModelProperty(value = "skuId", position = 1)
    @NotNull(message = "skuId不能为空")
    private Long skuId;

    @ApiModelProperty(value = "数量", position = 2)
    @NotNull(message = "数量不能为空")
    @Min(value = 1, message = "数量不能小于1")
    private Integer number;
}
