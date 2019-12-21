package com.dmall.pms.api.dto.categoryattribute.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 分类属性表 商品分类和商品属性的中间列表请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:17
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListCategoryAttributeRequestDTO", description = "分类属性表 商品分类和商品属性的中间列表请求实体")
public class ListCategoryAttributeRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




    @ApiModelProperty(value = "分类id", position = 2)
    private Long categoryId;


    @ApiModelProperty(value = "分类path", position = 3)
    private String cascadeCategoryId;


    @ApiModelProperty(value = "属性id", position = 4)
    private Long attributeId;


    @ApiModelProperty(value = "是否可筛选 1-不可筛选;2-单选;3-多选", position = 5)
    private Integer canScreen;












}
