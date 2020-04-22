package com.dmall.pms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 类型
 * @author: created by hang.yu on 2019/12/5 23:04
 */
@Getter
@AllArgsConstructor
public enum AttributeTypeEnum implements CodeDescEnum<Integer> {

    /**
     * 规格
     */
    SPECIFICATIONS(1, "规格"),

    /**
     * 参数
     */
    PARAMS(2, "参数"),
    ;

    private final Integer code;

    private final String desc;
}
