package com.dmall.bms.service.impl.permission.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 权限错误枚举
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Getter
@AllArgsConstructor
public enum PermissionErrorEnum implements ErrorCodeEnum {

    CODE_EXIST("1000","权限码已存在"),
    URI_METHOD_ERROR("1002","接口地址和请求方式不能为空"),
    URI_METHOD_EXIST("1003","该接口地址和请求方式已存在"),
    CATALOG_NOT_EXIST("1004","该目录不存在"),
    CATALOG_ERROR("1005","所选上级不是目录"),

    PERMISSION_NOT_EXIST("2000","该权限不存在"),

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
