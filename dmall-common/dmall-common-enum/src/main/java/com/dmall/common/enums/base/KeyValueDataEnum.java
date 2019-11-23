package com.dmall.common.enums.base;

/**
 * @description: key-value-data基类
 * @author: created by hang.yu on 2019/11/7 21:11
 */
public interface KeyValueDataEnum<T> extends KeyValueEnum {

    /**
     * 数据
     */
    T getData();
}
