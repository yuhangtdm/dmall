package com.dmall.common.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: YNEnum
 * @author: created by hang.yu on 2019/11/24 15:01
 */
@Getter
@AllArgsConstructor
public enum YNEnum implements CodeDescEnum<String> {

    /**
     * 是
     */
    Y("Y", "是"),

    /**
     * 否
     */
    N("N", "否");

    private final String code;

    private final String desc;

}
