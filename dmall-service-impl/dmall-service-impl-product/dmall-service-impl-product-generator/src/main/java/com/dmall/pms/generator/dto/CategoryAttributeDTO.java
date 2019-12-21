package com.dmall.pms.generator.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 分类属性表 商品分类和商品属性的中间表
 * @author: created by hang.yu on 2019-12-19 20:57:17
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CategoryAttributeDTO", description = "分类属性表 商品分类和商品属性的中间表")
public class CategoryAttributeDTO implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "分类id", position = 2)
    private Long categoryId;

    @ApiModelProperty(value = "分类path", position = 3)
    private String cascadeCategoryId;

    @ApiModelProperty(value = "属性id", position = 4)
    private Long attributeId;

    @ApiModelProperty(value = "是否可筛选 1-不可筛选;2-单选;3-多选", position = 5)
    private Integer canScreen;

    @ApiModelProperty(value = "创建人", position = 6)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 7)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 8)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 9)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 10)
    private String isDeleted;

}
