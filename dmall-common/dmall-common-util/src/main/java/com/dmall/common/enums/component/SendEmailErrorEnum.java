package com.dmall.common.enums.component;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 发送邮件异常枚举类 50300开头
 * @author: created by hang.yu on 2019/11/12 23:42
 */
@Getter
@AllArgsConstructor
public enum SendEmailErrorEnum implements ErrorCodeEnum {

    /**
     * 邮件服务器异常
     */
    BASIC_ERROR("50300", "邮件服务器异常"),

    /**
     * 发件人未配置
     */
    SEND_FROM_NULL("50301", "发件人未配置"),

    ;

    private final String code;

    private final String msg;
}
