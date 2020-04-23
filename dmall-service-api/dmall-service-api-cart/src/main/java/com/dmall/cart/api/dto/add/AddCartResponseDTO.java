package com.dmall.cart.api.dto.add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 新增购物车响应实体
 * @author: created by hang.yu on 2020/3/14 15:38
 */
@Data
@ApiModel(value = "AddCartResponseDTO", description = "新增购物车响应实体")
public class AddCartResponseDTO implements Serializable {

    private static final long serialVersionUID = -3502323342644824079L;

    @ApiModelProperty(value = "skuId", position = 1)
    private Long skuId;

    @ApiModelProperty(value = "sku名称", position = 2)
    private String skuName;

    @ApiModelProperty(value = "sku规格", position = 3)
    private String skuSpecificationsJson;

    @ApiModelProperty(value = "添加的数量", position = 4)
    private Integer number;

    @ApiModelProperty(value = "sku单价", position = 5)
    private BigDecimal price;

    @ApiModelProperty(value = "sku总价", position = 6)
    private BigDecimal totalPrice;
}
