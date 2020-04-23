package com.dmall.mms.api.dto.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @description: RegisterMemberRequestDTO
 * @author: created by hang.yu on 2020/2/23 21:53
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "RegisterMemberRequestDTO", description = "会员注册请求实体")
public class RegisterMemberRequestDTO implements Serializable {

    private static final long serialVersionUID = 7937806512918693943L;

    @ApiModelProperty(value = "会员邮箱", position = 1)
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty(value = "手机号", position = 2)
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty(value = "会员名称", position = 3)
    @NotBlank(message = "会员名称不能为空")
    private String memberName;

    @ApiModelProperty(value = "密码", position = 4)
    @NotBlank(message = "密码不能为空")
    private String password;
}
