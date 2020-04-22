package com.dmall.pms.api.dto.sku.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 校验订单请求实体
 * @author: created by hang.yu on 2020/3/29 11:40
 */
@Data
@ApiModel(value = "CheckOrderSkuRequestDTO", description = "校验订单请求实体")
public class CheckOrderSkuRequestDTO implements Serializable {

    private static final long serialVersionUID = 6205798068684074150L;

    @ApiModelProperty(value = "skuId", required = true, position = 1)
    @NotNull(message = "skuId不能为空")
    private Long skuId;

    @ApiModelProperty(value = "数量", required = true, position = 1)
    @NotNull(message = "数量不能为空")
    private Integer count;

    @ApiModelProperty(value = "sku单价", required = true, position = 1)
    @NotNull(message = "sku单价不能为空不能为空")
    private BigDecimal skuPrice;
}

