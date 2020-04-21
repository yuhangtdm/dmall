package com.dmall.bms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 权限类型
 * @author: created by hang.yu on 2020/1/16 23:24
 */
@Getter
@AllArgsConstructor
public enum PermissionTypeEnum implements CodeDescEnum<Integer> {

    /**
     * 接口地址
     */
    URI(1, "接口地址"),

    /**
     * 目录
     */
    CATALOG(2, "目录"),

    /**
     * 菜单
     */
    MENU(3, "菜单");

    private final Integer code;

    private final String desc;


}
