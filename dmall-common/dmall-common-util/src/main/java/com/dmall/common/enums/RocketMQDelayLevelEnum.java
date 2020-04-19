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

    /**
     * 1s
     */
    ONE(1, "1s"),

    /**
     * 5s
     */
    TWO(2, "5s"),

    /**
     * 10s
     */
    THREE(3, "10s"),

    /**
     * 30s
     */
    Four(4, "30s"),

    /**
     * 1m
     */
    FIVE(5, "1m"),

    /**
     * 2m
     */
    SIX(6, "2m"),

    /**
     * 3m
     */
    SEVEN(7, "3m"),

    /**
     * 4m
     */
    EIGHT(8, "4m"),

    /**
     * 5m
     */
    NINE(9, "5m"),

    /**
     * 6m
     */
    TEN(10, "6m"),

    /**
     * 7m
     */
    ELEVEN(11, "7m"),

    /**
     * 8m
     */
    TWELVE(12, "8m"),

    /**
     * 9m
     */
    THIRTEEN(13, "9m"),

    /**
     * 10m
     */
    FOURTEEN(14, "10m"),

    /**
     * 20m
     */
    FIFTEEN(15, "20m"),

    /**
     * 30m
     */
    SIXTEEN(16, "30m"),

    /**
     * 1h
     */
    SEVENTEEN(17, "1h"),

    /**
     * 2h
     */
    EIGHTEEN(18, "2h"),

    ;
    private final Integer code;

    private final String desc;
}
