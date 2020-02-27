package com.dmall.mms.service.impl.member.enums;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: SourceTypeEnum
 * @author: created by hang.yu on 2020/2/23 22:11
 */
@Getter
@AllArgsConstructor
public enum  SourceTypeEnum implements KeyValueEnum<Integer> {

    REGISTER(1,"注册"),
    QQ(2,"QQ"),
    WECHAT(3,"微信")

    ;
    private Integer code;

    private String desc;
}
