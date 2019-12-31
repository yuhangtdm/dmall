package com.dmall.pms.api.dto.attributetype.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @description: 新增属性分类请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SaveAttributeTypeRequestDTO", description = "新增属性分类请求实体")
public class SaveAttributeTypeRequestDTO implements Serializable {

    private static final long serialVersionUID = 7821691533714257307L;

    @ApiModelProperty(value = "展示名称", required = true, position = 1)
    @NotBlank(message = "展示名称不能为空")
    private String showName;
}
