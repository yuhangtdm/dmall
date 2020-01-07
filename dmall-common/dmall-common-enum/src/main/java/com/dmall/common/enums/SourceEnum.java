package com.dmall.common.enums;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: SourceEnum
 * @author: created by hang.yu on 2020/1/7 22:22
 */
@Getter
@AllArgsConstructor
public enum SourceEnum implements KeyValueEnum<String> {
    ADMIN("admin", "后台"),
    PC("pc", "PC官网"),
    APP("app", "APP"),
    WECHAT("wechat", "微信小程序"),
    ;
    private String code;

    private String desc;
}