package com.dmall.mms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: InvoiceHeaderEnum
 * @author: created by hang.yu on 2020/3/27 23:42
 */
@Getter
@AllArgsConstructor
public enum InvoiceHeaderEnum implements CodeDescEnum<Integer> {

    PERSONAL(1, "个人"),
    COMPANY(2, "公司");
    /**
     * code
     */
    private Integer code;

    /**
     * desc
     */
    private String desc;
}
