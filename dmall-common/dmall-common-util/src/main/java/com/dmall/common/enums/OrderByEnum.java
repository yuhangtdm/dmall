package com.dmall.common.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/4 22:00
 */
@Getter
@AllArgsConstructor
public enum OrderByEnum implements CodeDescEnum<String> {

    /**
     * 升序
     */
    ASC("ASC", "升序"),

    /**
     * 降序
     */
    DESC("DESC", "降序"),
    ;
    private final String code;

    private final String desc;
}
