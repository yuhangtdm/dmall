package com.dmall.mms.service.impl.coupon.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 会员优惠券表 错误枚举
 * @author: created by hang.yu on 2020-02-22 23:31:53
 */
@Getter
@AllArgsConstructor
public enum CouponErrorEnum implements ErrorCodeEnum {

    COUPON_NOT_EXIST("coupon_100","该会员优惠券表 不存在"),

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
