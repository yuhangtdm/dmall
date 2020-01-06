package com.dmall.service.sso.admin;

import com.dmall.common.enums.base.ErrorCodeEnum;
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
    PASSWORD_INCORRECT("login_004", "密码不正确"),
    USER_INVALID("login_005", "用户状态非法"),
    TOKEN_INVALID("login_006", "token不能为空"),
    USER_NOT_LOGIN("login_007", "用户登录失效"),
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
