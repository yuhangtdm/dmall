package com.dmall.pay.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 支付状态枚举
 * @author: created by hang.yu on 2020/4/3 23:16
 */
@Getter
@AllArgsConstructor
public enum PaymentStatusEnum implements CodeDescEnum<Integer> {
    /**
     * 待支付
     */
    WAIT_PAY(1, "待支付"),

    /**
     * 支付成功
     */
    PAY_SUCCESS(2, "支付成功"),

    /**
     * 支付失败
     */
    PAY_FAILED(3, "支付失败");

    private Integer code;

    private String desc;
}
