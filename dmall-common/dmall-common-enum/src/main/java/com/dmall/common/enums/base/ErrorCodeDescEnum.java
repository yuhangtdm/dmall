package com.dmall.common.enums.base;

/**
 * @description: 错误码+详细描述枚举基类
 * @author: created by hang.yu on 2019/11/7 21:08
 */
public interface ErrorCodeDescEnum extends ErrorCodeEnum{

    /**
     * 错误详细描述
     */
    String getDesc();
}
