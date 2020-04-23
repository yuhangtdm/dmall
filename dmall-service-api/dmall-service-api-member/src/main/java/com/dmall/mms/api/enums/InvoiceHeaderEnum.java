package com.dmall.mms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 发票头类型枚举
 * @author: created by hang.yu on 2020/3/27 23:42
 */
@Getter
@AllArgsConstructor
public enum InvoiceHeaderEnum implements CodeDescEnum<Integer> {

    /**
     * 个人
     */
    PERSONAL(1, "个人"),

    /**
     * 公司
     */
    COMPANY(2, "公司");
    /**
     * code
     */
    private final Integer code;

    /**
     * desc
     */
    private final String desc;
}
