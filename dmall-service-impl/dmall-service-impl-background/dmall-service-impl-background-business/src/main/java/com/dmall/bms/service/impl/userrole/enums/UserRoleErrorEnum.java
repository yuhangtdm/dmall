package com.dmall.bms.service.impl.userrole.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 后台用户-角色错误枚举
 * @author: created by hang.yu on 2020-01-05 18:36:38
 */
@Getter
@AllArgsConstructor
public enum UserRoleErrorEnum implements ErrorCodeEnum {

    SAVE_USERROLE_ERROR("userRole-001", "新增后台用户-角色失败"),
    UPDATE_USERROLE_ERROR("userRole _002", "修改后台用户-角色失败"),
    DELETE_USERROLE_ERROR("userRole_003", "删除后台用户-角色失败"),
    USERROLE_NOT_EXIST("userRole_004", "该后台用户-角色不存在"),

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
