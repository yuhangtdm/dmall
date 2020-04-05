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
    ADMIN("admin", "后台"),
    PORTAL("portal", "PC官网"),
    ;
    private String code;

    private String desc;
}