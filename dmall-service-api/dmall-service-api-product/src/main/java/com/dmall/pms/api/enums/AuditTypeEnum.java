package com.dmall.pms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 审核类型枚举
 * @author: created by hang.yu on 2020/4/25 15:35
 */
@Getter
@AllArgsConstructor
public enum AuditTypeEnum implements CodeDescEnum<Integer> {

    /**
     * sku上架
     */
    SKU_ON_PUBLISH(1, "sku上架"),

    /**
     * sku下架
     */
    SKU_OFF_PUBLISH(2, "sku下架"),

    /**
     * 修改sku信息
     */
    SKU_UPDATE(3, "修改sku信息"),

    ;

    private final Integer code;

    private final String desc;
}
