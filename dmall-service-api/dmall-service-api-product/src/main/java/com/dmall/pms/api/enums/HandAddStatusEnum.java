package com.dmall.pms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 是否支持手动新增
 * @author: created by hang.yu on 2019/12/7 23:01
 */
@Getter
@AllArgsConstructor
public enum HandAddStatusEnum implements CodeDescEnum<String> {

    /**
     * 支持
     */
    Y("Y", "支持"),

    /**
     * 不支持
     */
    N("N", "不支持"),
    ;

    private final String code;

    private final String desc;
}
