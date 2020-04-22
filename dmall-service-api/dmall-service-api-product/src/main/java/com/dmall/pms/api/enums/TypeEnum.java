package com.dmall.pms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 属性类型
 * @author: created by hang.yu on 2019/12/5 23:08
 */
@Getter
@AllArgsConstructor
public enum TypeEnum implements CodeDescEnum<Integer> {

    /**
     * 普通属性
     */
    NORMAL(1, "普通属性"),

    /**
     * 公共属性
     */
    PUBLIC(2, "公共属性");

    private final Integer code;

    private final String desc;
}
