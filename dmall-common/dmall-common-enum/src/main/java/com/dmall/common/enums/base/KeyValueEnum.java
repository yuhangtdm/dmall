package com.dmall.common.enums.base;

import java.io.Serializable;

/**
 * @description: key-value枚举基类
 * @author: created by hang.yu on 2019/11/7 21:03
 */
public interface KeyValueEnum<CODE> extends Serializable {

    /**
     * 错误码
     */
    CODE getCode();

    /**
     * 错误描述
     */
    String getDesc();

}
