package com.dmall.mms.api.dto.member.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @description: ForgetPasswordRequestDTO
 * @author: created by hang.yu on 2020/2/27 23:09
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ForgetPasswordRequestDTO", description = "忘记密码请求实体")
public class ForgetPasswordRequestDTO implements Serializable {

    private static final long serialVersionUID = 4471734284920340088L;

    @ApiModelProperty(value = "会员邮箱", position = 1)
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty(value = "验证码", position = 2)
    @NotBlank(message = "验证码不能为空")
    private String code;

    @ApiModelProperty(value = "新密码", position = 3)
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

}
