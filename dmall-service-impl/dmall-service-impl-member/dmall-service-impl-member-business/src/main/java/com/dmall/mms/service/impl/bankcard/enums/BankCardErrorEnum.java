package com.dmall.mms.service.impl.bankcard.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员银行卡错误枚举
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Getter
@AllArgsConstructor
public enum BankCardErrorEnum implements ErrorCodeEnum {

    BANKCARD_NOT_EXIST("bankCard_100","该会员银行卡不存在"),

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
