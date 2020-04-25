package com.dmall.sso.api.dto.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @description: 后台登录请求实体
 * @author: created by hang.yu on 2020/1/6 23:10
 */
@Data
@ApiModel(value = "AdminLoginRequestDTO", description = "后台登录请求实体")
public class AdminLoginRequestDTO {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", required = true, position = 1)
    private String userName;

    @ApiModelProperty(value = "密码", required = true, position = 2)
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "登录后跳转的地址", position = 3)
    private String url;
}
