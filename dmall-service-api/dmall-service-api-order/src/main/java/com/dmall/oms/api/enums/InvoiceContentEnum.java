package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 发票内容枚举
 * @author: created by hang.yu on 2020/4/7 21:49
 */
@Getter
@AllArgsConstructor
public enum  InvoiceContentEnum implements CodeDescEnum<Integer> {
    CATEGORY(1, "商品类别"),
    DETAIL(2, "商品明细"),
    ;
    private Integer code;

    private String desc;
}
