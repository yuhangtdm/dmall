package com.dmall.pms.api.dto.category.request.setattribute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @description: 设置属性请求实体
 * @author: created by hang.yu on 2019/12/25 22:59
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SetAttributeRequestDTO", description = "设置属性请求实体")
public class SetAttributeRequestDTO {

    @ApiModelProperty(value = "分类id", required = true, position = 1)
    @NotNull(message = "分类id不能为空")
    private Long categoryId;

    @ApiModelProperty(value = "属性分类列表", required = true, position = 2)
    @Valid
    @NotNull(message = "属性列表不能为空")
    @Size(min = 1, message = "属性列表不能为空")
    private List<AttributeIdsDTO> attributes;
}
