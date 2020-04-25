package com.dmall.pay.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: RefundStatusEnum
 * @author: created by hang.yu on 2020/4/13 23:38
 */
@Getter
@AllArgsConstructor
public enum RefundStatusEnum implements CodeDescEnum<Integer> {

    /**
     * 退款中
     */
    REFUND_ING(1, "退款中"),

    /**
     * 退款成功
     */
    REFUND_SUCCESS(2, "退款成功"),

    /**
     * 退款失败
     */
    REFUND_FAIL(3, "退款失败");

    private final Integer code;

    private final String desc;
}
