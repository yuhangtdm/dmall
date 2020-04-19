package com.dmall.common.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: IsDeletedEnum
 * @author: created by hang.yu on 2019/11/23 10:40
 */
@Getter
@AllArgsConstructor
public enum IsDeletedEnum implements CodeDescEnum {

    /**
     * Y
     */
    Y("Y", "不可用"),

    /**
     * N
     */
    N("N", "可用");

    private final String code;

    private final String desc;

}
