package com.dmall.common.model.exception;

import lombok.Getter;

/**
 * @description: 组件异常
 * @author: created by yuhang on 2019/11/7 20:56
 */
@Getter
public class ComponentException extends RuntimeException {

    private String code;

    private String msg;

    public ComponentException(String code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ComponentException(String msg, Throwable cause){
        super(msg,cause);
        this.msg = msg;
    }

}
