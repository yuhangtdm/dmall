package com.dmall.mms.api.dto.member.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author: created by hang.yu on 2020/3/1 17:53
 */
@Getter
@AllArgsConstructor
public enum SourceTypeEnum implements CodeDescEnum<Integer> {
    REGISTER(1, "用户注册"),
    WECHAT(2, "微信"),
    QQ(3, "QQ"),
    WEIBO(4, "微博"),
    ;
    private Integer code;

    private String desc;
}
