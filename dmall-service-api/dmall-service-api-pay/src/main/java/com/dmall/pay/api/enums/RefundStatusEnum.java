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

    REFUND_ING(1, "退款中"),

    REFUND_SUCCESS(2, "退款成功"),

    REFUND_FAIL(3, "退款失败");

    private Integer code;

    private String desc;
}
