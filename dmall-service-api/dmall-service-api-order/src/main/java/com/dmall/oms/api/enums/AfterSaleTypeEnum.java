package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 售后类型枚举
 * @author: created by hang.yu on 2020/4/14 22:18
 */
@Getter
@AllArgsConstructor
public enum AfterSaleTypeEnum implements CodeDescEnum<Integer> {

    /**
     * 退款
     */
    REFUND(1, "退款"),

    /**
     * 退货退款
     */
    RETURN(2, ""),

    /**
     * 换货
     */
    CHANGE(3, "换货");

    /**
     * code
     */
    private final Integer code;

    /**
     * desc
     */
    private final String desc;
}
