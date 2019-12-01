package com.dmall.mms.api.dto.membercollectionsku.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 会员收藏sku列表请求实体
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ListMemberCollectionSkuRequestDTO", description="会员收藏sku列表请求实体")
public class ListMemberCollectionSkuRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




    @ApiModelProperty(value = "会员id", position = 2)
    private Long memberId;


    @ApiModelProperty(value = "skuid", position = 3)
    private Long skuId;


    @ApiModelProperty(value = "商品id", position = 4)
    private Long productId;


    @ApiModelProperty(value = "商品编号", position = 5)
    private String productNo;


    @ApiModelProperty(value = "sku编号", position = 6)
    private String skuNo;


    @ApiModelProperty(value = "收藏时价格", position = 7)
    private BigDecimal price;












}
