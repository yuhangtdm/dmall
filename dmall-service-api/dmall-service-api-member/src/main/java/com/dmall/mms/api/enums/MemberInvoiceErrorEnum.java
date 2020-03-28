package com.dmall.mms.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员错误枚举
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Getter
@AllArgsConstructor
public enum MemberInvoiceErrorEnum implements ErrorCodeEnum {

    PERSONAL_NAME_EMPTY("1001","个人名称不能为空"),

    COMPANY_NAME_EMPTY("1002","单位名称不能为空"),

    CUSTOMER_TAX_NUMBER_EMPTY("1003","纳税人识别号不能为空"),

    INVOICE_NOT_EXISTS("2001","该发票不存在"),

    MEMBER_INVOICE_EXISTS("2002","该会员没有发票"),
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
