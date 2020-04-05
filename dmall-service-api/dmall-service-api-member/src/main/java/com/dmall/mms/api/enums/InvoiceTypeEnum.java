package com.dmall.mms.api.enums;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 发票类型枚举
 * @author: created by hang.yu on 2020/3/27 23:42
 */
@Getter
@AllArgsConstructor
public enum InvoiceTypeEnum implements KeyValueEnum<Integer> {

    NO_NEED(0, "无需发票"),

    Electronics(1, "电子发票"),

    PAPER(2, "纸质发票");
    /**
     * code
     */
    private Integer code;

    /**
     * desc
     */
    private String desc;
}
