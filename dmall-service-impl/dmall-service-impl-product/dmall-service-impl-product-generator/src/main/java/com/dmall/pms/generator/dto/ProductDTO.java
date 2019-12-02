package com.dmall.pms.generator.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 商品表
 * @author: created by hang.yu on 2019-12-02 23:55:34
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ProductDTO", description="商品表")
public class ProductDTO implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "商家店铺id", position = 2)
    private Long merchantsId;

    @ApiModelProperty(value = "商品分类id", position = 3)
    private Long categoryId;

    @ApiModelProperty(value = "品牌id", position = 4)
    private Long brandId;

    @ApiModelProperty(value = "运费模板id", position = 5)
    private Long feightTemplateId;

    @ApiModelProperty(value = "商品编号", position = 6)
    private String productNo;

    @ApiModelProperty(value = "商品名称", position = 7)
    private String name;

    @ApiModelProperty(value = "商品图片", position = 8)
    private String pic;

    @ApiModelProperty(value = "商品描述", position = 9)
    private String description;

    @ApiModelProperty(value = "单位", position = 10)
    private String unit;

    @ApiModelProperty(value = "重量 默认克", position = 11)
    private BigDecimal weight;

    @ApiModelProperty(value = "备注", position = 12)
    private String remark;

    @ApiModelProperty(value = "商品属性", position = 13)
    private String productAttribute;

    @ApiModelProperty(value = "品牌名称", position = 14)
    private String brandName;

    @ApiModelProperty(value = "上市时间", position = 15)
    private Date onMarketTime;

    @ApiModelProperty(value = "商品最高价", position = 16)
    private BigDecimal maxPrice;

    @ApiModelProperty(value = "商品最低价", position = 17)
    private BigDecimal minPrice;

    @ApiModelProperty(value = "销量", position = 18)
    private Integer saleCount;

    @ApiModelProperty(value = "浏览量", position = 19)
    private Integer viewCount;

    @ApiModelProperty(value = "评论量", position = 20)
    private Integer commentCount;

    @ApiModelProperty(value = "好评量", position = 21)
    private Integer goodCommentCount;

    @ApiModelProperty(value = "中评量", position = 22)
    private Integer middleCommentCount;

    @ApiModelProperty(value = "差评量", position = 23)
    private Integer badCommentCount;

    @ApiModelProperty(value = "好评率", position = 24)
    private BigDecimal goodRate;

    @ApiModelProperty(value = "平均评分", position = 25)
    private BigDecimal commentScore;

    @ApiModelProperty(value = "商品分类id集合 如:1/2/3", position = 26)
    private String cascadeCategoryId;

    @ApiModelProperty(value = "增值业务", position = 27)
    private String addServices;

    @ApiModelProperty(value = "创建人", position = 28)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 29)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 30)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 31)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 32)
    private String isDeleted;

}
