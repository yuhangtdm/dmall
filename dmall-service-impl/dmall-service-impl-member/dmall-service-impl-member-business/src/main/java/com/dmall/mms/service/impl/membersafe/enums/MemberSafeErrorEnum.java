package com.dmall.mms.service.impl.membersafe.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 账户安全错误枚举
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Getter
@AllArgsConstructor
public enum MemberSafeErrorEnum implements ErrorCodeEnum {

    MEMBERSAFE_NOT_EXIST("memberSafe_100","该账户安全不存在"),

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
