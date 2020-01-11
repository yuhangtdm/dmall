package com.dmall.bms.service.impl.userloginlog.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 后台用户登录日志错误枚举
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Getter
@AllArgsConstructor
public enum UserLoginLogErrorEnum implements ErrorCodeEnum {

    SAVE_USERLOGINLOG_ERROR("userLoginLog-001", "新增后台用户登录日志失败"),
    UPDATE_USERLOGINLOG_ERROR("userLoginLog _002", "修改后台用户登录日志失败"),
    DELETE_USERLOGINLOG_ERROR("userLoginLog_003", "删除后台用户登录日志失败"),
    USERLOGINLOG_NOT_EXIST("userLoginLog_004", "该后台用户登录日志不存在"),

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
