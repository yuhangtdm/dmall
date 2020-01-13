package com.dmall.bms.service.impl.user.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 后台用户错误枚举
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Getter
@AllArgsConstructor
public enum UserErrorEnum implements ErrorCodeEnum {

    USER_NOT_EXIST("user_100","该后台用户不存在"),

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
