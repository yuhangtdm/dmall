package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: JobTypeEnum
 * @author: created by hang.yu on 2020/4/18 21:34
 */
@Getter
@AllArgsConstructor
public enum JobTypeEnum implements CodeDescEnum<Integer> {

    /**
     * 自动收货
     */
    AUTO_RECEIVE(1, "自动收货"),

    /**
     * 自动好评
     */
    AUTO_GOOD_COMMENT(2, "自动好评"),
    ;

    private final Integer code;

    private final String desc;
}
