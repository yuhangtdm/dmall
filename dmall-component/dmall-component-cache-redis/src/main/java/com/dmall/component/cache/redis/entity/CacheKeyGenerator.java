package com.dmall.component.cache.redis.entity;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
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
     * 生成key的方法
     */
    public static String generate(Object o, Method method, Object... args){
        StringBuilder key = new StringBuilder();
        String[] cacheNames = method.getAnnotation(Cacheable.class).cacheNames();
        key.append(cacheNames[0]).append(StrUtil.COLON);
        // 类名.方法名
        key.append(o.getClass().getName()).append(StrUtil.DOT).append(method.getName()).append(StrUtil.COLON);
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (ObjectUtil.isNotEmpty(args[i])){
                    // 判断是否为简单类型
                    if (ClassUtil.isSimpleValueType(args[i].getClass())){
                        key.append(args[i]).append("|").append(args[i]);
                    }else {
                        // 非复杂类型时
                        if (!ObjectUtil.isComplexObject(args[i])){
                            Field[] fields = ReflectUtil.getFields(args[i].getClass());
                            for (Field field : fields) {
                                Object fieldValue = ReflectUtil.getFieldValue(args[i], field);
                                if (ObjectUtil.isNotEmpty(fieldValue) && !ObjectUtil.isComplexObject(fieldValue)){
                                    key.append(field.getName()).append("|").append(fieldValue);
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
