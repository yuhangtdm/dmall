package com.dmall.oms.api.dto.totrade.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description: 跳转结算页sku信息
 * @author: created by hang.yu on 2020/3/26 22:35
 */
@Data
@ApiModel(value = "TradeSkuRequestDTO", description = "跳转结算页sku信息")
public class TradeSkuRequestDTO {

    @ApiModelProperty(value = "skuId", position = 1)
    @NotNull(message = "skuId不能为空")
    private Long skuId;

    @ApiModelProperty(value = "count", position = 1)
    @NotNull(message = "数量不能为空")
    private Integer count;
}
