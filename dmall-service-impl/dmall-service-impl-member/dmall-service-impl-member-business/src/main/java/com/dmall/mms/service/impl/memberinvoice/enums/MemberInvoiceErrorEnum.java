package com.dmall.mms.service.impl.memberinvoice.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员发票错误枚举
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Getter
@AllArgsConstructor
public enum  MemberInvoiceErrorEnum implements ErrorCodeEnum {

    SAVE_MEMBERINVOICE_ERROR("memberInvoice-001","新增会员发票失败"),
    UPDATE_MEMBERINVOICE_ERROR("memberInvoice _002","修改会员发票失败"),
    DELETE_MEMBERINVOICE_ERROR("memberInvoice_003","删除会员发票失败"),
    MEMBERINVOICE_NOT_EXIST("memberInvoice_004","该会员发票不存在"),

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
