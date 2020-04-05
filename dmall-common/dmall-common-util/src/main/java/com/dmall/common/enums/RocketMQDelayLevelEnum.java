package com.dmall.common.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: RocketMQDelayLevelEnum
 * @author: created by hang.yu on 2020/3/28 18:13
 */
@Getter
@AllArgsConstructor
public enum RocketMQDelayLevelEnum implements CodeDescEnum<Integer> {

    ONE(1, "1s"),
    TWO(2, "5s"),
    THREE(3, "10s"),
    Four(4, "30s"),
    FIVE(5, "1m"),
    SIX(6, "2m"),
    SEVEN(7, "3m"),
    EIGHT(8, "4m"),
    NINE(9, "5m"),
    TEN(10, "6m"),
    ELEVEN(11, "7m"),
    TWELVE(12, "8m"),
    THIRTEEN(13, "9m"),
    FOURTEEN(14, "10m"),
    FIFTEEN(15, "20m"),
    SIXTEEN(16, "30m"),
    SEVENTEEN(17, "1h"),
    EIGHTEEN(18, "2h"),

    ;
    private Integer code;

    private String desc;
}
