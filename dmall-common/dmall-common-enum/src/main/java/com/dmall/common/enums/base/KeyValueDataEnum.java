package com.dmall.common.enums.base;

/**
 * @description: key-value-data基类
 * @author: created by hang.yu on 2019/11/7 21:11
 */
public interface KeyValueDataEnum<T,CODE> extends KeyValueEnum<CODE> {

    /**
     * 数据
     */
    T getData();
}
