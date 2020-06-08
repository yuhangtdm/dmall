package com.dmall.mms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 发票内容枚举
 * @author: created by hang.yu on 2020/3/27 23:45
 */
@Getter
@AllArgsConstructor
public enum InvoiceContentEnum implements CodeDescEnum<Integer> {

    /**
     * 商品分类
     */
    CATEGORY(1, "商品分类"),

    /**
     * 商品明细
     */
    DETAIL(2, "商品明细");

    /**
     * code
     */
    private final Integer code;

    /**
     * desc
     */
    private final String desc;
}
