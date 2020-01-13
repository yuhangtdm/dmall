package com.dmall.bms.service.impl.userloginlog.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 后台用户登录日志错误枚举
 * @author: created by hang.yu on 2020-01-13 23:04:04
 */
@Getter
@AllArgsConstructor
public enum UserLoginLogErrorEnum implements ErrorCodeEnum {

    USERLOGINLOG_NOT_EXIST("userLoginLog_100","该后台用户登录日志不存在"),

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
