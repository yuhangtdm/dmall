package com.dmall.pms.api.dto.category.request.setattributetype;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description: 设置属性分类
 * @author: created by hang.yu on 2019/12/5 21:16
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SetAttributeTypeRequestDTO", description = "设置属性分类请求实体")
public class SetAttributeTypeRequestDTO {

    @ApiModelProperty(value = "分类id", required = true, position = 1)
    @NotNull(message = "分类id不能为空")
    private Long categoryId;

    @ApiModelProperty(value = "属性分类列表", required = true, position = 2)
    @Valid
    @NotNull(message = "属性分类列表不能为空")
    private List<AttributeTypeIdsDTO> attributeTypes;
}
