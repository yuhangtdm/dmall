package com.dmall.mms.service.impl.memberinvoice.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员发票错误枚举
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Getter
@AllArgsConstructor
public enum MemberInvoiceErrorEnum implements ErrorCodeEnum {

    MEMBERINVOICE_NOT_EXIST("memberInvoice_100","该会员发票不存在"),

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
