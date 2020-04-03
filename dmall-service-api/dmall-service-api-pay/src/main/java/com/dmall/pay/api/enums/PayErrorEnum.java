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
public enum PayErrorEnum implements ErrorCodeEnum {

    /**
     * 该订单已创建支付单
     */
    PAYMENT_ORDER_EXISTS("1000", "该订单已创建支付单"),

    CREATE_ALI_ERROR("1001", "支付宝异常,请稍后再试"),

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
