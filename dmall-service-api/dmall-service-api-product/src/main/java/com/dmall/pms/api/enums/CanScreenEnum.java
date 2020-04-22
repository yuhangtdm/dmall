package com.dmall.pms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: CanScreenEnum
 * @author: created by hang.yu on 2019/12/25 23:15
 */
@Getter
@AllArgsConstructor
public enum CanScreenEnum implements CodeDescEnum<Integer> {

    /**
     * 不可筛选
     */
    NOT(1, "不可筛选"),

    /**
     * 单选
     */
    SINGLE(2, "单选"),

    /**
     * 多选
     */
    MULTI(3, "多选"),
    ;

    private final Integer code;

    private final String desc;
}
