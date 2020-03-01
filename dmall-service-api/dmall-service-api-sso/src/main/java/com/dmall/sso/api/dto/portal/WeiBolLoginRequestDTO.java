package com.dmall.sso.api.dto.portal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @description: 前台商城登录请求实体
 * @author: created by hang.yu on 2020/1/6 23:10
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "WeiBolLoginRequestDTO", description = "前台商城登录请求实体")
public class WeiBolLoginRequestDTO {

    @NotBlank(message = "id不能为空")
    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @NotBlank(message = "会员名不能为空")
    @ApiModelProperty(value = "会员名", position = 2)
    private String memberName;

    @ApiModelProperty(value = "密码", position = 3)
    private String password;

    @ApiModelProperty(value = "真实姓名", position = 4)
    private String realName;

    @ApiModelProperty(value = "昵称", position = 5)
    private String nickName;

    @ApiModelProperty(value = "手机号", position = 6)
    private String phone;

    @ApiModelProperty(value = "邮箱", position = 7)
    private String email;

    @ApiModelProperty(value = "头像", position = 8)
    private String icon;

    @ApiModelProperty(value = "用户来源", position = 9)
    private Integer sourceType;

    @ApiModelProperty(value = "微博id", position = 10)
    private Long weiBoId;
}
