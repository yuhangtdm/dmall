package com.dmall.sso.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: LoginTypeEnum
 * @author: created by hang.yu on 2020/2/29 15:12
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnum implements CodeDescEnum<String> {

    PC("1", "pc网站"),
    // 2-android;3-ios;4-小程序

    ;

    private final String code;

    private final String desc;
}
