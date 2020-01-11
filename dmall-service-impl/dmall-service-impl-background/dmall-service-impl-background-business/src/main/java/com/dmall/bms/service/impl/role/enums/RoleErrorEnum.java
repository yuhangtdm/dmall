package com.dmall.bms.service.impl.role.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 后台角色错误枚举
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Getter
@AllArgsConstructor
public enum RoleErrorEnum implements ErrorCodeEnum {

    SAVE_ROLE_ERROR("role-001", "新增后台角色失败"),
    UPDATE_ROLE_ERROR("role _002", "修改后台角色失败"),
    DELETE_ROLE_ERROR("role_003", "删除后台角色失败"),
    ROLE_NOT_EXIST("role_004", "该后台角色不存在"),

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
