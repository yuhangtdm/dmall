package com.dmall.pms.api.dto.sku.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description: 库存SKU请求实体
 * @author: created by hang.yu on 2020/3/28 23:01
 */
@Data
@ApiModel(value = "SkuStockRequestDTO", description = "库存SKU请求实体")
public class SkuStockRequestDTO {

    @ApiModelProperty(value = "skuId", position = 5)
    @NotNull(message = "skuId不能为空")
    private Long skuId;

    @ApiModelProperty(value = "数量", position = 5)
    @NotNull(message = "数量不能为空")
    private Integer number;
}
