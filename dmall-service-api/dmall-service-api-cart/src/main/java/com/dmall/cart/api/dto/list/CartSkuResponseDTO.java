package com.dmall.cart.api.dto.list;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 购物车列表sku响应实体
 * @author: created by hang.yu on 2020/3/14 15:54
 */
@Data
@ApiModel(value = "CartSkuResponseDTO", description = "购物车列表sku响应实体")
public class CartSkuResponseDTO implements Serializable {

    private static final long serialVersionUID = -755948561683037853L;

    @ApiModelProperty(value = "skuId", position = 1)
    private Long skuId;

    @ApiModelProperty(value = "sku名称", position = 2)
    private String skuName;

    @ApiModelProperty(value = "sku规格", position = 3)
    private String skuSpecificationsJson;

    @ApiModelProperty(value = "添加的数量", position = 4)
    private Integer number;

    @ApiModelProperty(value = "sku单价", position = 5)
    private BigDecimal skuPrice;

    @ApiModelProperty(value = "sku总价", position = 6)
    private BigDecimal skuTotalPrice;

    @ApiModelProperty(value = "是否有货", position = 7)
    private Boolean hasStock;

    @ApiModelProperty(value = "上架状态,Y-上架;N-下架", position = 8)
    private Boolean status;

    @ApiModelProperty(value = "是否降价", position = 3)
    private Boolean hasReductionPrice;

    @ApiModelProperty(value = "下降的价钱", position = 10)
    private BigDecimal reductionPrice;

    @ApiModelProperty(value = "是否勾选", position = 11)
    private Boolean checked;
}
