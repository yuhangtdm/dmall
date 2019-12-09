package com.dmall.pms.api.dto.attributetype.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 属性分类公共请求实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@ApiModel(value="CommonAttributeTypeRequestDTO", description="属性分类公共请求实体")
public class CommonAttributeTypeRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "名称", position = 2)
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "展示名称", position = 3)
    @NotBlank(message = "展示名称不能为空")
    private String showName;

}
