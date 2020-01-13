package com.dmall.bms.service.impl.rolepermission.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 后台角色-资源错误枚举
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Getter
@AllArgsConstructor
public enum RolePermissionErrorEnum implements ErrorCodeEnum {

    ROLEPERMISSION_NOT_EXIST("rolePermission_100","该后台角色-资源不存在"),

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
