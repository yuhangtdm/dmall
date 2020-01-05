package com.dmall.bms.service.impl.user.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 后台用户错误枚举
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Getter
@AllArgsConstructor
public enum UserErrorEnum implements ErrorCodeEnum {

    SAVE_USER_ERROR("user-001", "新增后台用户失败"),
    UPDATE_USER_ERROR("user _002", "修改后台用户失败"),
    DELETE_USER_ERROR("user_003", "删除后台用户失败"),
    USER_NOT_EXIST("user_004", "该后台用户不存在"),

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
