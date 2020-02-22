package com.dmall.bms.service.impl.user.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 后台用户错误枚举
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Getter
@AllArgsConstructor
public enum UserErrorEnum implements ErrorCodeEnum {

    USER_NAME_EXIST("1000","用户名称已存在"),

    USER_NOT_EXIST("2000","该用户不存在"),

    UPLOAD_PIC_ERROR("3000","上传头像失败"),

    ROLE_ID_NOT_EXIST("4000","角色id不存在"),

    PERMISSION_ID_NOT_EXIST("4000","权限id不存在"),
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
