package com.dmall.bms.api.dto.permission.request;

import com.dmall.bms.api.enums.HttpMethodEnum;
import com.dmall.common.dto.validate.ValueInEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description: 修改权限请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@ApiModel(value = "UpdatePermissionRequestDTO", description = "修改权限请求实体")
public class UpdatePermissionRequestDTO {

    @ApiModelProperty(value = "主键", required = true, position = 1)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "权限码", position = 2)
    private String code;

    @ApiModelProperty(value = "权限名称", position = 3)
    private String name;

    @ApiModelProperty(value = "权限地址", position = 4)
    private String uri;

    @ApiModelProperty(value = "请求方式", position = 5)
    @ValueInEnum(HttpMethodEnum.class)
    private String method;

}
