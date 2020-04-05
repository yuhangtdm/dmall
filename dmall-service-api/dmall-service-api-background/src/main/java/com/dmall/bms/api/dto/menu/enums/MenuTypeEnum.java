package com.dmall.bms.api.dto.menu.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author: created by hang.yu on 2020/2/22 14:19
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum implements CodeDescEnum<Integer> {

    CATALOG(1, "目录"),
    MENU(2, "菜单");
    private Integer code;

    private String desc;
}
