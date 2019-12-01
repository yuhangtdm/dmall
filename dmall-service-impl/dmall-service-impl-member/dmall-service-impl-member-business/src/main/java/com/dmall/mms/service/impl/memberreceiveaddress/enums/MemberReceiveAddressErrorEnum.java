package com.dmall.mms.service.impl.memberreceiveaddress.enums;

import com.dmall.common.enums.base.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员收货地址错误枚举
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Getter
@AllArgsConstructor
public enum  MemberReceiveAddressErrorEnum implements ErrorCodeEnum {

    SAVE_MEMBERRECEIVEADDRESS_ERROR("memberReceiveAddress-001","新增会员收货地址失败"),
    UPDATE_MEMBERRECEIVEADDRESS_ERROR("memberReceiveAddress _002","修改会员收货地址失败"),
    DELETE_MEMBERRECEIVEADDRESS_ERROR("memberReceiveAddress_003","删除会员收货地址失败"),
    MEMBERRECEIVEADDRESS_NOT_EXIST("memberReceiveAddress_004","该会员收货地址不存在"),

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
