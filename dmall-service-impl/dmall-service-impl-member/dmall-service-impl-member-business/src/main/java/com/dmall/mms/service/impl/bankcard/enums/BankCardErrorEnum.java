package com.dmall.mms.service.impl.bankcard.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员银行卡错误枚举
 * @author: created by hang.yu on 2019-12-01 22:56:07
 */
@Getter
@AllArgsConstructor
public enum  BankCardErrorEnum implements ErrorCodeEnum {

    SAVE_BANKCARD_ERROR("bankCard-001","新增会员银行卡失败"),
    UPDATE_BANKCARD_ERROR("bankCard _002","修改会员银行卡失败"),
    DELETE_BANKCARD_ERROR("bankCard_003","删除会员银行卡失败"),
    BANKCARD_NOT_EXIST("bankCard_004","该会员银行卡不存在"),

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
