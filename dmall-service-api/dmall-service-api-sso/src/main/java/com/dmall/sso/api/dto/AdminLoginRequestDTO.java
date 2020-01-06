package com.dmall.sso.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @author: created by hang.yu on 2020/1/6 23:10
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AdminLoginRequestDTO", description = "后台登录请求实体")
public class AdminLoginRequestDTO {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String password;
}
