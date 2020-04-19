package com.dmall.common.util;

import cn.hutool.core.util.ReflectUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @description: FieldUtil
 * @author: created by hang.yu on 2020/4/19 16:21
 */
public class FieldUtil {

    private FieldUtil() {
    }

    /**
     * 获取对象属性的第一个注解
     */
    public static <T extends Annotation> T findAnnotationField(Object object, Class<T> annotationClass) {
        Field[] fields = ReflectUtil.getFields(object.getClass());
        for (Field field : fields) {
            T t = field.getAnnotation(annotationClass);
            if (t != null) {
                return t;
            }
        }
        return null;
    }
}
