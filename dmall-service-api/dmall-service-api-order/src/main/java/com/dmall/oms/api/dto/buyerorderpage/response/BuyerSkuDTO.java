package com.dmall.oms.api.dto.buyerorderpage.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 买家端子订单商品响应实体
 * @author: created by hang.yu on 2020/4/9 22:34
 */
@Data
@ApiModel(value = "BuyerSkuDTO", description = "买家端子订单商品响应实体")
public class BuyerSkuDTO {

    @ApiModelProperty(value = "skuId", position = 1)
    private Long skuId;

    @ApiModelProperty(value = "sku名称", position = 2)
    private String skuName;

    @ApiModelProperty(value = "sku主图", position = 3)
    private String skuMainPic;

    @ApiModelProperty(value = "sku数量", position = 4)
    private Integer skuNumber;

    @ApiModelProperty(value = "sku支付总价", position = 5)
    private BigDecimal skuTotalPrice;
}
