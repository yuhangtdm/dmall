package com.dmall.pms.api.dto.product.request.attribute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 新增商品属性类型实体
 * @author: created by hang.yu on 2019/12/10 22:11
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AttributeTypeDTO", description = "新增商品属性类型实体")
public class AttributeTypeDTO implements Serializable {

    private static final long serialVersionUID = -3230869580283698520L;

    @ApiModelProperty(value = "属性分类id", position = 1)
    @NotNull(message = "属性分类id不能为空")
    private Long attributeTypeId;

    @ApiModelProperty(value = "属性集合", position = 1)
    @NotNull(message = "属性集合不能为空")
    private List<AttributeDTO> attributes;
}
