package com.dmall.pms.api.dto.attributetype.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 属性分类列表请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ListAttributeTypeRequestDTO", description="属性分类列表请求实体")
public class ListAttributeTypeRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




    @ApiModelProperty(value = "商品分类id", position = 2)
    private Long productCategoryId;


    @ApiModelProperty(value = "商品分类id集合 如，1/2/3", position = 3)
    private String cascadeCategoryId;


    @ApiModelProperty(value = "名称", position = 4)
    private String name;


    @ApiModelProperty(value = "展示名称", position = 5)
    private String showName;


    @ApiModelProperty(value = "排序", position = 6)
    private Integer sort;


    @ApiModelProperty(value = "规格数量", position = 7)
    private Integer specificationsCount;


    @ApiModelProperty(value = "参数数量", position = 8)
    private Integer paramCount;












}
