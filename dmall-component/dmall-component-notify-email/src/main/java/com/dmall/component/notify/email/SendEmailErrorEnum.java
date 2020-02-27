package com.dmall.component.notify.email;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 发送邮件异常枚举类
 * @author: created by hang.yu on 2019/11/12 23:42
 */
@Getter
@AllArgsConstructor
public enum SendEmailErrorEnum implements ErrorCodeEnum {

    BASIC_ERROR("1000", "发送邮件失败"),

    ;

    private String code;

    private String msg;
}
