package com.dmall.mms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: InvoiceContentEnum
 * @author: created by hang.yu on 2020/3/27 23:45
 */
@Getter
@AllArgsConstructor
public enum InvoiceContentEnum implements CodeDescEnum<Integer> {

    CATEGORY(1, "商品分类"),
    DETAIL(2, "商品明细");
    /**
     * code
     */
    private Integer code;

    /**
     * desc
     */
    private String desc;
}
