package com.dmall.common.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: SourceEnum
 * @author: created by hang.yu on 2020/1/7 22:22
 */
@Getter
@AllArgsConstructor
public enum SourceEnum implements CodeDescEnum<String> {

    /**
     * 后台管理系统
     */
    ADMIN("admin", "后台管理系统"),

    /**
     * 商城前台
     */
    PORTAL("portal", "商城前台"),
    ;
    private final String code;

    private final String desc;
}