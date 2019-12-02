package com.dmall.pms.generator.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: sku属性表
 * @author: created by hang.yu on 2019-12-02 23:55:34
 */
@Data
@Accessors(chain = true)
@ApiModel(value="SkuAttributeDTO", description="sku属性表")
public class SkuAttributeDTO implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "商品id", position = 2)
    private Long productId;

    @ApiModelProperty(value = "skuid", position = 3)
    private Long skuId;

    @ApiModelProperty(value = "属性id", position = 4)
    private Long attributeId;

    @ApiModelProperty(value = "属性值id", position = 5)
    private Long valueId;

    @ApiModelProperty(value = "属性名称", position = 6)
    private String attributeName;

    @ApiModelProperty(value = "属性值", position = 7)
    private String value;

    @ApiModelProperty(value = "类型 1-规格;2-参数", position = 8)
    private Integer type;

    @ApiModelProperty(value = "规格配图", position = 9)
    private String pic;

    @ApiModelProperty(value = "商品编号", position = 10)
    private String productNo;

    @ApiModelProperty(value = "sku编号", position = 11)
    private String skuNo;

    @ApiModelProperty(value = "创建人", position = 12)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 13)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 14)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 15)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 16)
    private String isDeleted;

}
