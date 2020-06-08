package com.dmall.bms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: MenuTargetEnum
 * @author: created by hang.yu on 2020/5/13 20:30
 */
@Getter
@AllArgsConstructor
public enum MenuTargetEnum implements CodeDescEnum<String> {

    SELF("_self", "_self"),

    BLANK("_blank", "_blank"),
    ;

    private final String code;

    private final String desc;
}
