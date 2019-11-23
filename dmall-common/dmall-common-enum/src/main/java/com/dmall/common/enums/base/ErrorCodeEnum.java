package com.dmall.common.enums.base;

import java.io.Serializable;

/**
 * @description: 错误码枚举标准类
 * @author: created by hang.yu on 2019/11/7 20:59
 */
public interface ErrorCodeEnum extends Serializable {

    /**
     * 错误码
     */
    String getCode();

    /**
     * 错误描述
     */
    String getMsg();

}
