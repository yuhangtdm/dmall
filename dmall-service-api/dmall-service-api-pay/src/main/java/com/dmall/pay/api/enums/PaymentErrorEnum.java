package com.dmall.pay.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 支付枚举
 * @author: created by hang.yu on 2020/4/3 23:12
 */
@Getter
@AllArgsConstructor
public enum PaymentErrorEnum implements ErrorCodeEnum {

    /**
     * 该订单已创建支付单
     */
    PAYMENT_ORDER_EXISTS("1000", "该订单已创建支付单"),

    /**
     * 支付宝异常,请稍后再试
     */
    CREATE_ALI_ERROR("1001", "支付宝异常,请稍后再试"),

    /**
     * 订单不存在
     */
    OUT_TRADE_NO_NOT_EXISTS("1003", "订单不存在"),

    /**
     * 支付方式不存在
     */
    PAYMENT_TYPE__NOT_EXISTS("1004", "支付方式不存在"),

    /**
     * 退款失败
     */
    REFUND_ERROR("2000", "退款失败"),


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
