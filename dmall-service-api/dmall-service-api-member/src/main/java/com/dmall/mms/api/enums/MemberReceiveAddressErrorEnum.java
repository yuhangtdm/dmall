package com.dmall.mms.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员收货地址错误枚举
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Getter
@AllArgsConstructor
public enum MemberReceiveAddressErrorEnum implements ErrorCodeEnum {


    MEMBER_RECEIVE_ADDRESS_COUNT_LIMIT("1000","收货地址最多只有20个"),

    MEMBER_RECEIVE_ADDRESS_NOT_EXIST("2000","该会员收货地址不存在"),
    MEMBER_RECEIVE_ADDRESS_UPDATE_ERROR("2001","您不能操作他人的地址"),


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
