package com.dmall.oms.api.dto.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: OrderItemDTO
 * @author: created by hang.yu on 2020/4/6 10:37
 */
@Data
@ApiModel(value = "BuyerOrderItemDTO", description = "sku信息")
public class BuyerOrderItemDTO {

    @ApiModelProperty(value = "orderItemId", position = 1)
    private Long orderItemId;

    @ApiModelProperty(value = "skuId", position = 2)
    private Long skuId;

    @ApiModelProperty(value = "sku名称", position = 3)
    private String skuName;

    @ApiModelProperty(value = "sku单价", position = 4)
    private BigDecimal skuPrice;

    @ApiModelProperty(value = "sku数量", position = 5)
    private Integer skuNumber;

    @ApiModelProperty(value = "sku主图", position = 6)
    private String skuMainPic;

    @ApiModelProperty(value = "sku总价", position = 7)
    private BigDecimal skuTotalPrice;
}
