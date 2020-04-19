package com.dmall.common.model.portal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 缓存商城用户实体
 * @author: created by hang.yu on 2020/1/6 22:23
 */
@Data
public class PortalMemberDTO implements Serializable {

    private static final long serialVersionUID = 8813933971551287235L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "会员名", position = 2)
    private String memberName;

    @ApiModelProperty(value = "昵称", position = 3)
    private String nickName;

    @ApiModelProperty(value = "手机号", position = 4)
    private String phone;

    @ApiModelProperty(value = "邮箱", position = 5)
    private String email;

    @ApiModelProperty(value = "真实姓名", position = 6)
    private String realName;

    @ApiModelProperty(value = "来源 admin/portal", position = 7)
    private String source;

    @ApiModelProperty(value = "微信号", position = 8)
    private String weChatNo;

    @ApiModelProperty(value = "qq号", position = 9)
    private String qq;

    @ApiModelProperty(value = "微博号", position = 10)
    private Long weiBoNo;

    @ApiModelProperty(value = "头像", position = 11)
    private String icon;

    @ApiModelProperty(value = "密码", position = 12)
    private String password;

    @ApiModelProperty(value = "会员来源", position = 13)
    private Integer sourceType;

    @ApiModelProperty(value = "token", position = 14)
    private String token;
}