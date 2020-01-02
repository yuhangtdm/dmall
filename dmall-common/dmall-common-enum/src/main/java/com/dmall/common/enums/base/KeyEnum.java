package com.dmall.common.enums.base;

import java.io.Serializable;

/**
 * @description:
 * @author: created by hang.yu on 2020/1/2 22:14
 */
public interface KeyEnum<CODE> extends Serializable {
    /**
     * 错误码
     */
    CODE getCode();
}
