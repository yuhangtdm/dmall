package com.dmall.mms.api.dto.member.enums;

import com.dmall.common.enums.base.KeyValueDataEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: GenderEnum
 * @author: created by hang.yu on 2020/3/1 21:38
 */
@Getter
@AllArgsConstructor
public enum GenderEnum implements KeyValueDataEnum<Integer, String> {
    // m：男、f：女、n：未知
    MALE("m","男", 1),
    FEMALE("f","女", 2),
    SECRECY("n","未知", 3),
    ;
    private String code;

    private String desc;

    private Integer data;
}
