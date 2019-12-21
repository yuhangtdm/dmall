package com.dmall.pms.api.dto.categoryattribute.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.pms.api.dto.categoryattribute.common.CommonCategoryAttributeRequestDTO;

/**
 * @description: 修改分类属性表 商品分类和商品属性的中间请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:17
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UpdateCategoryAttributeRequestDTO", description = "修改分类属性表 商品分类和商品属性的中间请求实体")
public class UpdateCategoryAttributeRequestDTO extends CommonCategoryAttributeRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 0)
    private Long id;

}
