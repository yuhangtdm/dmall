package com.dmall.bms.service.impl.roleresource.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 后台角色-资源错误枚举
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Getter
@AllArgsConstructor
public enum RoleResourceErrorEnum implements ErrorCodeEnum {

    SAVE_ROLERESOURCE_ERROR("roleResource-001", "新增后台角色-资源失败"),
    UPDATE_ROLERESOURCE_ERROR("roleResource _002", "修改后台角色-资源失败"),
    DELETE_ROLERESOURCE_ERROR("roleResource_003", "删除后台角色-资源失败"),
    ROLERESOURCE_NOT_EXIST("roleResource_004", "该后台角色-资源不存在"),

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
