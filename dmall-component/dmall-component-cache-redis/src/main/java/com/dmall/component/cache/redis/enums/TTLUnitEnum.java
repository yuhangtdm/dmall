package com.dmall.component.cache.redis.enums;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 过期单位枚举
 * @author: created by hang.yu on 2019/11/3 21:55
 */
@Getter
@AllArgsConstructor
public enum TTLUnitEnum implements KeyValueEnum<Integer> {

    SECOND(1, "秒"),
    MINUTE(2,"分钟"),
    HOUR(3,"小时"),
    DAY(3,"天")
    ;

    /**
     * code
     */
    private Integer code;

    /**
     * desc
     */
    private String desc;

}
