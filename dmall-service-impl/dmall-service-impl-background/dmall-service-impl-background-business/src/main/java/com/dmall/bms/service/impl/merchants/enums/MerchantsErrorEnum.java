package com.dmall.bms.service.impl.merchants.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 商家店铺表 1期只有一家店铺错误枚举
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Getter
@AllArgsConstructor
public enum MerchantsErrorEnum implements ErrorCodeEnum {

    MERCHANTS_NOT_EXIST("merchants_100","该商家店铺表 1期只有一家店铺不存在"),

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
