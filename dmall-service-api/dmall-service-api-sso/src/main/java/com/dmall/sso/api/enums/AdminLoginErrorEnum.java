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
public enum AdminLoginErrorEnum implements ErrorCodeEnum {

    USER_NAME_INCORRECT("1000", "用户名不存在"),
    PASSWORD_INCORRECT("1001", "密码错误"),
    USER_INVALID("1002", "账号已被锁定,请联系管理员"),
    AUTHENTICATION_FAILED("1003", "认证系统异常"),
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
