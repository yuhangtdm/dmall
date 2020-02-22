package com.dmall.mms.service.impl.memberreceiveaddress.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员收货地址错误枚举
 * @author: created by hang.yu on 2020-02-22 23:31:54
 */
@Getter
@AllArgsConstructor
public enum MemberReceiveAddressErrorEnum implements ErrorCodeEnum {

    MEMBERRECEIVEADDRESS_NOT_EXIST("memberReceiveAddress_100","该会员收货地址不存在"),

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
