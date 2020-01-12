package com.dmall.sso.service.impl.admin;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 登录错误枚举
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Getter
@AllArgsConstructor
public enum LoginErrorEnum implements ErrorCodeEnum {

    USER_NAME_BLANK("login_001", "用户名不能为空"),
    PASSWORD_BLANK("login_002", "密码不能为空"),
    USER_NAME_INCORRECT("login_003", "用户名不存在"),
    PASSWORD_INCORRECT("login_004", "密码错误"),
    USER_INVALID("login_005", "账号已被锁定,请联系管理员"),
    TOKEN_INVALID("login_006", "token不能为空"),
    AUTHENTICATION_FAILED("login_008", "认证失败"),
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
