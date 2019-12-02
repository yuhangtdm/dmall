package com.dmall.pms.api.dto.categorybrand.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 分类品牌关系公共请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@ApiModel(value="CommonCategoryBrandRequestDTO", description="分类品牌关系公共请求实体")
public class CommonCategoryBrandRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;




    @ApiModelProperty(value = "商品分类id", position = 2)
    private Long productCategoryId;


    @ApiModelProperty(value = "品牌id", position = 3)
    private Long brandId;


    @ApiModelProperty(value = "排序", position = 4)
    private Integer sort;












}
