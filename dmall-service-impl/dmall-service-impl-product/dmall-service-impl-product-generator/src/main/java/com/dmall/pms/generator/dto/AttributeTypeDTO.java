package com.dmall.pms.generator.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 属性分类表
 * @author: created by hang.yu on 2019-12-19 20:57:17
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AttributeTypeDTO", description = "属性分类表")
public class AttributeTypeDTO implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "商品分类id", position = 2)
    private Long categoryId;

    @ApiModelProperty(value = "商品分类path", position = 3)
    private String cascadeCategoryId;

    @ApiModelProperty(value = "名称", position = 4)
    private String name;

    @ApiModelProperty(value = "展示名称", position = 5)
    private String showName;

    @ApiModelProperty(value = "排序", position = 6)
    private Integer sort;

    @ApiModelProperty(value = "创建人", position = 7)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 8)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 9)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 10)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 11)
    private String isDeleted;

}
