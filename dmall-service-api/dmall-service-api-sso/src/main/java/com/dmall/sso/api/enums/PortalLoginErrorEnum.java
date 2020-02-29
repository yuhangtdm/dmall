package com.dmall.sso.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 登录错误枚举
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Getter
@AllArgsConstructor
public enum PortalLoginErrorEnum implements ErrorCodeEnum {

    MEMBER_NAME_INCORRECT("1000", "您还不是会员,前去注册"),
    PASSWORD_INCORRECT("1001", "您的密码错误"),
    USER_INVALID("1002", "您的账号已被锁定,请联系管理员"),
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
