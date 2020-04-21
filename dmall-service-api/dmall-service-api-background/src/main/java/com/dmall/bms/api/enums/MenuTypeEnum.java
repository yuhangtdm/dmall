package com.dmall.bms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 菜单类型枚举
 * @author: created by hang.yu on 2020/2/22 14:19
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum implements CodeDescEnum<Integer> {

    /**
     * 目录
     */
    CATALOG(1, "目录"),

    /**
     * 菜单
     */
    MENU(2, "菜单");

    private final Integer code;

    private final String desc;
}
