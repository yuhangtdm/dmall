package com.dmall.search.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description: 导入es的dto
 * @author: created by hang.yu on 2020/3/5 22:03
 */
@Data
@ApiModel(value = "SkuEsResponseDTO", description = "es实体")
public class SkuEsResponseDTO {

    @ApiModelProperty(value = "skuId", position = 1)
    private Long skuId;

    @ApiModelProperty(value = "sku名称", position = 2)
    private String skuName;

    @ApiModelProperty(value = "sku编号", position = 3)
    private String skuNo;

    @ApiModelProperty(value = "sku价格", position = 4)
    private BigDecimal skuPrice;

    @ApiModelProperty(value = "sku副名称", position = 5)
    private String skuSubName;

    @ApiModelProperty(value = "sku描述", position = 6)
    private String skuDescription;

    @ApiModelProperty(value = "sku主图", position = 7)
    private String skuMainPic;

    @ApiModelProperty(value = "sku库存", position = 8)
    private Integer skuStock;

    @ApiModelProperty(value = "sku评论数量", position = 9)
    private Long skuCommentCount;

    @ApiModelProperty(value = "sku销量", position = 10)
    private Long skuSaleCount;

    @ApiModelProperty(value = "sku上架时间", position = 11)
    private String skuOnPublishTime;

    @ApiModelProperty(value = "商品id", position = 12)
    private Long productId;

    @ApiModelProperty(value = "商品编号", position = 13)
    private String productNo;

    @ApiModelProperty(value = "商品名称", position = 14)
    private String productName;

    @ApiModelProperty(value = "品牌id", position = 15)
    private Long brandId;

}
