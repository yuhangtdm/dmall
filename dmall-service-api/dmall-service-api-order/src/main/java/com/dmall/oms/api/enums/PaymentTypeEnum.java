package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 支付方式枚举
 * @author: created by hang.yu on 2020/3/28 16:54
 */
@Getter
@AllArgsConstructor
public enum PaymentTypeEnum implements CodeDescEnum<Integer> {

    /**
     * 支付宝
     */
    ALI_PAY(1, "支付宝"),

    /**
     * 微信
     */
    WX_PAY(2, "微信"),

    /**
     * 银联
     */
    Union_Pay(3, "银联"),


    ;
    private Integer code;

    private String desc;
}
