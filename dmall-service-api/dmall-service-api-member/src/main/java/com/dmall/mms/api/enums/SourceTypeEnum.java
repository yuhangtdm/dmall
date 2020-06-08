package com.dmall.mms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 来源枚举
 * @author: created by hang.yu on 2020/3/1 17:53
 */
@Getter
@AllArgsConstructor
public enum SourceTypeEnum implements CodeDescEnum<Integer> {
    /**
     * 用户注册
     */
    REGISTER(1, "用户注册"),

    /**
     * 微信
     */
    WX(2, "微信"),

    /**
     * QQ
     */
    QQ(3, "QQ"),

    /**
     * 微博
     */
    WEIBO(4, "微博"),
    ;

    private final Integer code;

    private final String desc;
}
