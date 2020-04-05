package com.dmall.common.enums.base;

/**
 * @description: key-value-data基类
 * @author: created by hang.yu on 2019/11/7 21:11
 */
public interface CodeDescDataEnum<T, CODE> extends CodeDescEnum<CODE> {

    /**
     * 数据
     */
    T getData();
}
