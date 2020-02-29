package com.dmall.sso.api.dto.portal;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: LoginTypeEnum
 * @author: created by hang.yu on 2020/2/29 15:12
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnum implements KeyValueEnum<String> {

    PC("1","pc网站"),
    // 2-android;3-ios;4-小程序

    ;


    private String code;

    private String desc;
}
