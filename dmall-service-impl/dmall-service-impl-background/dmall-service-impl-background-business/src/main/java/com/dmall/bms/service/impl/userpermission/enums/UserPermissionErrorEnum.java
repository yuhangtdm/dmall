package com.dmall.bms.service.impl.userpermission.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限错误枚举
 * @author: created by hang.yu on 2020-01-13 23:04:04
 */
@Getter
@AllArgsConstructor
public enum UserPermissionErrorEnum implements ErrorCodeEnum {

    USERPERMISSION_NOT_EXIST("userPermission_100","该后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限不存在"),

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
