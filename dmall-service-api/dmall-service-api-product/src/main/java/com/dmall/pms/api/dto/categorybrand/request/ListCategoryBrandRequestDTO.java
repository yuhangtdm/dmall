package com.dmall.pms.api.dto.categorybrand.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 分类品牌关系列表请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ListCategoryBrandRequestDTO", description="分类品牌关系列表请求实体")
public class ListCategoryBrandRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




    @ApiModelProperty(value = "商品分类id", position = 2)
    private Long productCategoryId;


    @ApiModelProperty(value = "品牌id", position = 3)
    private Long brandId;


    @ApiModelProperty(value = "排序", position = 4)
    private Integer sort;












}
