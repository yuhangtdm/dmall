package com.dmall.pms.generator.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 属性值表
 * @author: created by hang.yu on 2019-12-16 15:14:50
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ProductAttributeDTO", description = "属性值表")
public class ProductAttributeDTO implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "商品id", position = 2)
    private Long productId;

    @ApiModelProperty(value = "属性分类id", position = 3)
    private Long attributeTypeId;

    @ApiModelProperty(value = "商品属性id", position = 4)
    private Long attributeId;

    @ApiModelProperty(value = "属性值", position = 5)
    private String attributeValue;

    @ApiModelProperty(value = "属性配图", position = 6)
    private String pic;

    @ApiModelProperty(value = "属性类型 1-规格;2-参数", position = 7)
    private Integer attributeType;

    @ApiModelProperty(value = "创建人", position = 8)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 9)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 10)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 11)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 12)
    private String isDeleted;

}
