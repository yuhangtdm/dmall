package com.dmall.pms.api.dto.attributetype.request;

import com.dmall.component.web.validate.ValueInEnum;
import com.dmall.pms.api.dto.attributetype.enums.AttributeTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @description: 修改属性分类请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "UpdateAttributeTypeRequestDTO", description = "修改属性分类请求实体")
public class UpdateAttributeTypeRequestDTO {

    @ApiModelProperty(value = "主键", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "名称", position = 1)
    private String name;

    @ApiModelProperty(value = "展示名称", position = 2)
    private String showName;

    @ApiModelProperty(value = "类型 1-规格;2-参数;", position = 4)
    @ValueInEnum(AttributeTypeEnum.class)
    @NotNull(message = "类型不能为空")
    private Integer type;

}
