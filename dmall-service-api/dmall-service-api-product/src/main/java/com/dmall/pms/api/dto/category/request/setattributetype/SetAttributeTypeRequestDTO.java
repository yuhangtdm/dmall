package com.dmall.pms.api.dto.category.request.setattributetype;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @description: 设置属性类别
 * @author: created by hang.yu on 2019/12/5 21:16
 */
@Data
@ApiModel(value = "SetAttributeTypeRequestDTO", description = "设置属性类别请求实体")
public class SetAttributeTypeRequestDTO {

    @ApiModelProperty(value = "分类id", required = true, position = 1)
    @NotNull(message = "分类id不能为空")
    private Long categoryId;

    @ApiModelProperty(value = "属性类别列表", required = true, position = 2)
    @Valid
    @NotNull(message = "属性类别列表不能为空")
    @Size(min = 1, message = "属性类别列表不能为空")
    private List<AttributeTypeIdsDTO> attributeTypes;
}
