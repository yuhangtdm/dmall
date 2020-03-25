package com.dmall.cart.api.dto.list;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description: 购物车列表响应实体
 * @author: created by hang.yu on 2020/3/14 15:52
 */
@Data
@ApiModel(value = "CartListResponseDTO", description = "购物车列表响应实体")
public class CartListResponseDTO {

    @ApiModelProperty(value = "sku商品总数量", position = 1)
    private Integer skuNumber;

    @ApiModelProperty(value = "购物车总数量", position = 2)
    private Integer totalNumber;

    @ApiModelProperty(value = "购物车总价格", position = 3)
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "选中的购物车总数量", position = 4)
    private Integer checkTotalNumber;

    @ApiModelProperty(value = "选中的购物车总价格", position = 5)
    private BigDecimal checkTotalPrice;

    @ApiModelProperty(value = "购物车列表", position = 6)
    private List<CartSkuResponseDTO> carts;
}
