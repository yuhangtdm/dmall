package com.dmall.mms.api.dto.membersku;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 会员收藏sku分页响应实体
 * @author: created by hang.yu on 2020-02-23 19:42:03
 */
@Data
@ApiModel(value = "MemberCollectionSkuResponseDTO", description = "会员收藏sku分页响应实体")
public class MemberCollectionSkuResponseDTO implements Serializable {

    private static final long serialVersionUID = 4216954960244783709L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "skuId", position = 2)
    private Long skuId;

    @ApiModelProperty(value = "商品id", position = 3)
    private Long productId;

    @ApiModelProperty(value = "商品编号", position = 4)
    private String productNo;

    @ApiModelProperty(value = "sku编号", position = 5)
    private String skuNo;

    @ApiModelProperty(value = "收藏时价格", position = 6)
    private BigDecimal price;

    @ApiModelProperty(value = "会员id", position = 7)
    private Long creator;

    @ApiModelProperty(value = "当前价格", position = 8)
    private BigDecimal currentPrice;

    @ApiModelProperty(value = "sku名称", position = 9)
    private String skuName;

    @ApiModelProperty(value = "降价标签", position = 10)
    private String priceReductionTag;

    @ApiModelProperty(value = "sku状态", position = 11)
    private String skuStatus;

}
