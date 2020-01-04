package com.dmall.pms.api.dto.category.request.setattributetype;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 属性分类列表实体
 * @author: created by hang.yu on 2019/12/4 23:12
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AttributeTypeIdsDTO" , description = "属性分类列表实体" )
public class AttributeTypeIdsDTO implements Serializable {

    @ApiModelProperty(value = "属性分类id" , required = true, position = 1)
    @NotNull(message = "属性分类id不能为空" )
    private Long attributeTypeId;

    @ApiModelProperty(value = "排序,大于等于1" , required = true, position = 2)
    @NotNull(message = "排序不能为空" )
    @Min(value = 1, message = "排序必须大于等于1" )
    private Integer sort;
}
