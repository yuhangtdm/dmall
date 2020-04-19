package com.dmall.common.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @description: 对象工具类
 * @author: created by hang.yu on 2019/11/23 10:06
 */
public class ObjectUtil extends StringUtils {

    private ObjectUtil() {
    }

    /**
     * 根据条件获取正确的值
     */
    public static <T> T or(boolean right, T t1, T t2) {
        return right ? t1 : t2;
    }

    /**
     * 如果对象不为空 则返回对象,否则返回默认值
     */
    public static Object defaultIfNull(Object object, Object defaultValue) {
        return isEmpty(object) ? defaultValue : object;
    }

    /**
     * 按照规则判断对象是否为空
     * 支持 字符串、集合、map、数组
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        // 字符串
        if (obj instanceof String) {
            if (ObjectUtil.isBlank((String) obj)) {
                return true;
            }
        }

        // 集合
        if (obj instanceof Collection) {
            Collection collection = (Collection) obj;
            if (collection.isEmpty()) {
                return true;
            }
            return collection.stream().allMatch(Objects::isNull);
        }

        //map
        if (obj instanceof Map) {
            Map map = (Map) obj;
            return map.isEmpty();
        }

        // 数组
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }

        return false;
    }

    /**
     * 按照规则判断对象是否非空
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 判断一组对象 是否有空对象
     */
    public static boolean containsEmpty(Object... obj) {
        for (Object o : obj) {
            if (isEmpty(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断一组对象 是否全部为空
     */
    public static boolean allEmpty(Object... obj) {
        for (Object o : obj) {
            if (isNotEmpty(o)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 判断一组对象 是否全部不为空
     */
    public static boolean allNotEmpty(Object... obj) {
        for (Object o : obj) {
            if (isEmpty(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否是复杂的引用类型
     */
    public static boolean complexObject(Object object) {
        if (object == null) {
            return false;
        }
        return object instanceof Collection
                || object instanceof Map
                || object.getClass().isArray();
    }


}
