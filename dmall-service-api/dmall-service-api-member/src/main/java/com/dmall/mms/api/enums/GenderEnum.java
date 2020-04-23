package com.dmall.mms.api.enums;

import com.dmall.common.enums.base.CodeDescDataEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 性别枚举
 * @author: created by hang.yu on 2020/3/1 21:38
 */
@Getter
@AllArgsConstructor
public enum GenderEnum implements CodeDescDataEnum<Integer, String> {
    /**
     * 男
     */
    MALE("m", "男", 1),

    /**
     * 女
     */
    FEMALE("f", "女", 2),

    /**
     * 未知
     */
    SECRECY("n", "未知", 3),
    ;
    private final String code;

    private final String desc;

    private final Integer data;
}
