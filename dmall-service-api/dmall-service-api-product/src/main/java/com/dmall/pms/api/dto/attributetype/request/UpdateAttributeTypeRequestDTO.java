package com.dmall.pms.api.dto.attributetype.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 修改属性类别请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@ApiModel(value = "UpdateAttributeTypeRequestDTO", description = "修改属性类别请求实体")
public class UpdateAttributeTypeRequestDTO implements Serializable {

    private static final long serialVersionUID = -4616472272818715346L;

    @ApiModelProperty(value = "主键", required = true, position = 1)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "展示名称", required = true, position = 2)
    @NotBlank(message = "展示名称不能为空")
    private String showName;

}
