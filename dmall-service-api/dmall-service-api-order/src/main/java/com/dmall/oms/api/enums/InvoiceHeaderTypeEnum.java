package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 发票抬头枚举
 * @author: created by hang.yu on 2020/4/7 21:47
 */
@Getter
@AllArgsConstructor
public enum InvoiceHeaderTypeEnum implements CodeDescEnum<Integer> {

    /**
     * 个人
     */
    PERSONAL(1, "个人"),

    /**
     * 公司
     */
    COMPANY(2, "公司"),
    ;
    private final Integer code;

    private final String desc;
}
