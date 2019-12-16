package com.dmall.pms.api.dto.product.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 商品列表请求实体
 * @author: created by hang.yu on 2019-12-16 15:14:50
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListProductRequestDTO", description = "商品列表请求实体")
public class ListProductRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




    @ApiModelProperty(value = "商家店铺id", position = 2)
    private Long merchantsId;


    @ApiModelProperty(value = "商品分类id", position = 3)
    private Long categoryId;


    @ApiModelProperty(value = "品牌id", position = 4)
    private Long brandId;


    @ApiModelProperty(value = "运费模板id", position = 5)
    private Long freightTemplateId;


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


    @ApiModelProperty(value = "上市时间", position = 14)
    private Date onMarketTime;


    @ApiModelProperty(value = "商品分类path", position = 15)
    private String cascadeCategoryId;


    @ApiModelProperty(value = "增值业务", position = 16)
    private String addServices;












}
