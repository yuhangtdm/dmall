package com.dmall.bms.service.impl.userrole.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 后台用户-角色错误枚举
 * @author: created by hang.yu on 2020-01-13 23:04:04
 */
@Getter
@AllArgsConstructor
public enum UserRoleErrorEnum implements ErrorCodeEnum {

    USERROLE_NOT_EXIST("userRole_100","该后台用户-角色不存在"),

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
