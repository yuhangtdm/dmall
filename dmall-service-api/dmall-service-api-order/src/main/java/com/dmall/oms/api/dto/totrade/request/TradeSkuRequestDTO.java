package com.dmall.oms.api.dto.totrade.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 跳转结算页sku信息
 * @author: created by hang.yu on 2020/3/26 22:35
 */
@Data
@ApiModel(value = "TradeSkuRequestDTO", description = "跳转结算页sku信息")
public class TradeSkuRequestDTO implements Serializable {

    private static final long serialVersionUID = 8029298336002517451L;

    @ApiModelProperty(value = "skuId", required = true, position = 1)
    @NotNull(message = "skuId不能为空")
    private Long skuId;

    @ApiModelProperty(value = "count", required = true, position = 1)
    @NotNull(message = "数量不能为空")
    @Min(value = 1, message = "数量必须大于0")
    private Integer count;
}
