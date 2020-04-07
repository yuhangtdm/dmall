package com.dmall.oms.api.dto.buyerdetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: SkuDTO
 * @author: created by hang.yu on 2020/4/6 10:37
 */
@Data
@ApiModel(value = "SkuDTO", description = "sku信息")
public class SkuDTO {

    @ApiModelProperty(value = "skuId", position = 1)
    private Long skuId;

    @ApiModelProperty(value = "sku名称", position = 2)
    private String skuName;

    @ApiModelProperty(value = "sku单价", position = 3)
    private BigDecimal skuPrice;

    @ApiModelProperty(value = "sku数量", position = 4)
    private Integer skuNumber;

    @ApiModelProperty(value = "sku图片", position = 5)
    private String skuPic;
}
