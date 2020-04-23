package com.dmall.oms.api.dto.createorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 创建订单sku信息
 * @author: created by hang.yu on 2020/3/28 15:49
 */
@Data
@ApiModel(value = "OrderSkuRequestDTO", description = "创建订单sku信息")
public class OrderSkuRequestDTO implements Serializable {

    private static final long serialVersionUID = -895614742198469787L;

    @ApiModelProperty(value = "skuId", required = true, position = 1)
    @NotNull(message = "skuId不能为空")
    private Long skuId;

    @ApiModelProperty(value = "数量", required = true, position = 1)
    @NotNull(message = "数量不能为空")
    private Integer number;

    @ApiModelProperty(value = "sku单价", required = true, position = 1)
    @NotNull(message = "sku单价不能为空不能为空")
    private BigDecimal skuPrice;

}
