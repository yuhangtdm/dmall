package com.dmall.bms.service.impl.permission.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 资源错误枚举
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Getter
@AllArgsConstructor
public enum PermissionErrorEnum implements ErrorCodeEnum {

    PERMISSION_NOT_EXIST("permission_100","该资源不存在"),

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
