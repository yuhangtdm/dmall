package com.dmall.common.util;

import com.alibaba.fastjson.JSON;

/**
 * @description: fast json工具类
 * @author: created by hang.yu on 2020/4/19 14:45
 */
public class JSONUtil {

    private JSONUtil() {

    }

    /**
     * 序列化为字符串
     */
    public static String toJSONString(Object object) {
        if (object == null) {
            return null;
        }
        return JSON.toJSONString(object);
    }

    /**
     * 序列化为字符串
     */
    public static String toJSONStringPretty(Object object) {
        if (object == null) {
            return null;
        }
        return JSON.toJSONString(object, true);
    }
}
