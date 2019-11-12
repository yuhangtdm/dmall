package com.dmall.common.model.result;

import lombok.Data;

/**
 * @description: 接口返回
 * @author: created by yuhang on 2019/11/7 21:32
 */
@Data
public class BaseResult<T> {

    /**
     * 返回结果
     */
    private Boolean result;

    /**
     * 返回码
     */
    private String code;
    /**
     * 返回消息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

}
