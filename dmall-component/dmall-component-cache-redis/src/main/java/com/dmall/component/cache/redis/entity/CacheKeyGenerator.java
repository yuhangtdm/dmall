package com.dmall.component.cache.redis.entity;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.common.constants.Constants;
import com.dmall.common.util.ObjectUtil;
import org.springframework.cache.annotation.Cacheable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @description: CacheKeyGenerator
 * @author: created by hang.yu on 2019/11/26 22:23
 */
public class CacheKeyGenerator {

    /**
     * Cacheable注解方法缓存生成key的方法
     */
    public static String generate(Object o, Method method, Object... args) {
        StringBuilder key = new StringBuilder();
        String[] cacheNames = method.getAnnotation(Cacheable.class).cacheNames();
        key.append(cacheNames[0]).append(StrUtil.COLON);
        // 类名.方法名
        key.append(o.getClass().getName()).append(StrUtil.DOT).append(method.getName()).append(StrUtil.COLON);
        if (args.length > 0) {
            for (Object arg : args) {
                if (ObjectUtil.isNotEmpty(arg)) {
                    // 如果为简单类型
                    if (ClassUtil.isSimpleValueType(arg.getClass())) {
                        key.append(arg).append(Constants.SPLIT).append(arg);
                    } else {
                        // 非复杂的类型 如 Collection、Map、数组
                        if (!ObjectUtil.complexObject(arg)) {
                            Field[] fields = ReflectUtil.getFields(arg.getClass());
                            for (Field field : fields) {
                                Object fieldValue = ReflectUtil.getFieldValue(arg, field);
                                if (ObjectUtil.isNotEmpty(fieldValue) && !ObjectUtil.complexObject(fieldValue)) {
                                    key.append(field.getName()).append(Constants.SPLIT).append(fieldValue);
                                }
                            }
                        }
                    }
                }
            }
        }
        return key.toString();
    }

}
