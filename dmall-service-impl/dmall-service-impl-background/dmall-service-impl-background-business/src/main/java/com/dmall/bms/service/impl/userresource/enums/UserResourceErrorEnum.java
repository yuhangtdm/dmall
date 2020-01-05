package com.dmall.bms.service.impl.userresource.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限错误枚举
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Getter
@AllArgsConstructor
public enum UserResourceErrorEnum implements ErrorCodeEnum {

    SAVE_USERRESOURCE_ERROR("userResource-001", "新增后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限失败"),
    UPDATE_USERRESOURCE_ERROR("userResource _002", "修改后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限失败"),
    DELETE_USERRESOURCE_ERROR("userResource_003", "删除后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限失败"),
    USERRESOURCE_NOT_EXIST("userResource_004", "该后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限不存在"),

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
