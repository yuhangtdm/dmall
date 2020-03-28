package com.dmall.oms.api.dto.createorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @description: 创建订单sku信息
 * @author: created by hang.yu on 2020/3/28 15:49
 */
@Data
@ApiModel(value = "OrderSkuRequestDTO", description = "创建订单sku信息")
public class OrderSkuRequestDTO {

    @ApiModelProperty(value = "skuId", position = 1)
    @NotNull(message = "skuId不能为空")
    private Long skuId;

    @ApiModelProperty(value = "数量", position = 1)
    @NotNull(message = "数量不能为空")
    private Integer count;

    @ApiModelProperty(value = "sku单价", position = 1)
    @NotNull(message = "sku单价不能为空不能为空")
    private BigDecimal skuPrice;

}
