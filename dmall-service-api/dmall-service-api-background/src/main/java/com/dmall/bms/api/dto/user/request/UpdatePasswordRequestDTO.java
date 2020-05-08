package com.dmall.bms.api.dto.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description: 用户修改密码请求实体
 * @author: created by hang.yu on 2020/5/8 21:07
 */
@Data
@ApiModel(value = "UpdatePasswordRequestDTO", description = "用户修改密码请求实体")
public class UpdatePasswordRequestDTO {

    @ApiModelProperty(value = "id", required = true, position = 1)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "原密码", required = true, position = 2)
    @NotBlank(message = "原密码不能为空")
    private String oldPassword;

    @ApiModelProperty(value = "新密码", required = true, position = 3)
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
