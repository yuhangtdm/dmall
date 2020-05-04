package com.dmall.sso.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: SsoErrorEnum  1600开头
 * @author: created by hang.yu on 2020/4/25 14:10
 */
@Getter
@AllArgsConstructor
public enum SsoErrorEnum implements ErrorCodeEnum {

    /**
     * 用户名不存在
     */
    USER_NAME_INCORRECT("1600", "用户名不存在"),

    /**
     * 密码错误
     */
    PASSWORD_INCORRECT("1601", "密码错误"),

    /**
     * 账号已被锁定,请联系管理员
     */
    USER_INVALID("1602", "账号已被锁定,请联系管理员"),

    /**
     * 认证系统异常
     */
    AUTHENTICATION_FAILED("1603", "认证系统异常"),

    /**
     * 您还不是会员,前去注册
     */
    MEMBER_NAME_INCORRECT("1604", "您还不是会员,前去注册"),

    /**
     * 您的密码错误
     */
    MEMBER_PASSWORD_INCORRECT("1605", "您的密码错误"),

    /**
     * 您的账号已被锁定,请联系管理员
     */
    MEMBER_INVALID("1606", "您的账号已被锁定,请联系管理员"),


    /**
     * 微博暂时无法正常使用
     */
    WEI_BO_ERROR("1607", "微博暂时无法正常使用"),

    ;
    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误描述
     */
    private final String msg;
}
