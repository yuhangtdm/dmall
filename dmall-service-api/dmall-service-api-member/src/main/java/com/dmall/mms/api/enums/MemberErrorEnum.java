package com.dmall.mms.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员错误枚举
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Getter
@AllArgsConstructor
public enum MemberErrorEnum implements ErrorCodeEnum {

    EMAIL_SEND("1000", "邮件已发送"),
    EMAIL_EXISTS("1001", "该邮箱已注册"),

    CHECK_CODE_EXPIRED("2000","验证码已过期"),
    CHECK_CODE_ERROR("2001","验证码错误"),

    REGISTER_PHONE_EXISTS("3000","该手机号已注册"),
    REGISTER_NAME_EXISTS("3001","该会员名已注册"),

    EMAIL_ERROR("4000","邮箱不正确"),
    PASSWORD_ERROR("4001","原密码错误"),

    ;

   /**
    * 错误码
    */
    private String code;

    /**
     * 错误描述
     */
    private String msg;

}
