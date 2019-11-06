package com.dmall.component.cache.redis.enums;

/**
 * @description: 过期单位枚举
 * @author: created by yuhang on 2019/11/3 21:55
 */
public enum TTLUnitEnum {

    SECOND(1, "秒"),
    MINUTE(2,"分钟"),
    HOUR(3,"小时"),
    DAY(3,"天")
    ;
    private Integer code;

    private String desc;

    TTLUnitEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
