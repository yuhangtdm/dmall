package com.dmall.pay.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 支付方式枚举
 * @author: created by hang.yu on 2020/4/3 22:52
 */
@Getter
@AllArgsConstructor
public enum PayTypeEnum implements CodeDescEnum<Integer> {
    /**
     * 支付宝
     */
    ALI_PAY(1, "支付宝"),

    /**
     * 微信支付
     */
    WX_PAY(2, "微信支付"),

    /**
     * 银联支付
     */
    UNION_PAY(3, "银联支付"),

    ;

    private Integer code;

    private String desc;
}
