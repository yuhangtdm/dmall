package com.dmall.bms.api.dto.permission.request;

import com.dmall.bms.api.enums.HttpMethodEnum;
import com.dmall.common.dto.validate.ValueInEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 新增权限请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@ApiModel(value = "SavePermissionRequestDTO", description = "新增权限请求实体")
public class SavePermissionRequestDTO implements Serializable {

    @ApiModelProperty(value = "服务id", required = true, position = 1)
    @NotNull(message = "服务id不能为空")
    private Long serviceId;

    @ApiModelProperty(value = "权限码", required = true, position = 2)
    @NotBlank(message = "权限码不能为空")
    private String code;

    @ApiModelProperty(value = "权限名称", required = true, position = 3)
    @NotBlank(message = "权限名称不能为空")
    private String name;

    @ApiModelProperty(value = "权限地址", required = true, position = 4)
    @NotBlank(message = "权限地址不能为空")
    private String uri;

    @ApiModelProperty(value = "请求方式", required = true, position = 5)
    @NotBlank(message = "请求方式不能为空")
    @ValueInEnum(HttpMethodEnum.class)
    private String method;

}
