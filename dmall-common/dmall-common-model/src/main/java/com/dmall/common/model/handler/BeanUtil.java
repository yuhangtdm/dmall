package com.dmall.common.model.handler;

import org.springframework.beans.BeanUtils;

/**
 * @description: BeanUtil
 * @author: created by hang.yu on 2019/11/19 23:42
 */
public class BeanUtil extends BeanUtils {

    /**
     * 重写copy属性的方法
     */
    public static <T> T copyProperties(Object source, Class<T> target){
        T t = instantiateClass(target);
        copyProperties(source,t);
        return t;
    }

}
