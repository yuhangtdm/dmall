package com.dmall.bms.service.impl.role.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 后台角色错误枚举
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Getter
@AllArgsConstructor
public enum RoleErrorEnum implements ErrorCodeEnum {

    NAME_EXIST("1000","角色名称已存在"),

    ROLE_NOT_EXIST("2000","该后台角色不存在"),

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
