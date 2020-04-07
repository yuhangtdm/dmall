package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 支付状态枚举
 * @author: created by hang.yu on 2020/3/28 16:57
 */
@Getter
@AllArgsConstructor
public enum PaymentStatusEnum implements CodeDescEnum<Integer> {

    /**
     * 待支付
     */
    NO_PAY(1, "待支付"),

    /**
     * 支付中
     */
    PAY_ING(2, "支付中"),

    /**
     * 支付成功
     */
    PAYED(3, "支付成功"),

    /**
     * 支付失败
     */
    PAY_ERR(4, "支付失败");
    private Integer code;

    private String desc;
}
