package com.dmall.mms.api.dto.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @description: ForgetPasswordRequestDTO
 * @author: created by hang.yu on 2020/2/27 23:09
 */
@Data
@ApiModel(value = "ResetPasswordRequestDTO", description = "重新密码请求实体")
public class ResetPasswordRequestDTO implements Serializable {

    private static final long serialVersionUID = 4471734284920340088L;

    @ApiModelProperty(value = "会员邮箱", required = true, position = 1)
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty(value = "验证码", required = true, position = 2)
    @NotBlank(message = "验证码不能为空")
    private String code;

    @ApiModelProperty(value = "新密码", required = true, position = 3)
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

}
