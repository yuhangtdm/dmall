package com.dmall.common.enums.base;

import java.io.Serializable;

/**
 * @description: CodeEnum
 * @author: created by hang.yu on 2020/1/2 22:14
 */
public interface CodeEnum<CODE> extends Serializable {
    /**
     * code
     */
    CODE getCode();
}
