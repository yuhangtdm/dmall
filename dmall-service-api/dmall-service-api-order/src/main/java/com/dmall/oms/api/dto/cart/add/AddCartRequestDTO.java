package com.dmall.oms.api.dto.cart.add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @description: 新增购物车请求实体
 * @author: created by hang.yu on 2020/3/12 23:14
 */
@Data
@ApiModel(value = "AddCartRequestDTO", description = "新增购物车请求实体")
public class AddCartRequestDTO {

    @ApiModelProperty(value = "skuId", position = 1)
    @NotNull(message = "skuId不能为空")
    private Long skuId;

    @ApiModelProperty(value = "数量", position = 2)
    @NotNull(message = "数量不能为空")
    @Min(value = 1, message = "数量不能小于1")
    private Integer number;
}
