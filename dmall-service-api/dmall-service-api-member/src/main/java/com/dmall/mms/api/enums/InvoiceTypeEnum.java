package com.dmall.mms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 发票类型枚举
 * @author: created by hang.yu on 2020/3/27 23:42
 */
@Getter
@AllArgsConstructor
public enum InvoiceTypeEnum implements CodeDescEnum<Integer> {

    /**
     * 无需发票
     */
    NO_NEED(0, "无需发票"),

    /**
     * 电子发票
     */
    ELECTRONICS(1, "电子发票"),

    /**
     * 纸质发票
     */
    PAPER(2, "纸质发票");

    /**
     * code
     */
    private final Integer code;

    /**
     * desc
     */
    private final String desc;
}
