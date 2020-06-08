package com.dmall.pms.api.dto.product.response.get;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: sku列表响应实体
 * @author: created by hang.yu on 2019/12/27 21:46
 */
@Data
@ApiModel(value = "SkuListResponseDTO", description = "sku列表响应实体")
public class SkuListResponseDTO implements Serializable {

    private static final long serialVersionUID = 3640609472202223262L;

    @ApiModelProperty(value = "sku编号", position = 1)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long skuId;

    @ApiModelProperty(value = "sku名称", position = 2)
    private String skuName;

    @ApiModelProperty(value = "价格", position = 3)
    private BigDecimal price;

    @ApiModelProperty(value = "库存", position = 4)
    private Integer stock;

    @ApiModelProperty(value = "规格", position = 5)
    private String specifications;
}
