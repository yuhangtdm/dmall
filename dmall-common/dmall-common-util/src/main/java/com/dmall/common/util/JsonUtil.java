package com.dmall.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;

/**
 * @description: jackson工具类
 * @author: created by hang.yu on 2020/4/20 20:32
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        // 对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        // 取消默认转换timestamps形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 统一日期格式
        objectMapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));
        // 忽略 json字符串中存在，但是在java对象中不存在属性的情况
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 允许json字符串有注释
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    }

    /**
     * 对象转json字符串
     */
    public static <T> String toJson(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String)obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("parse object to string error", e);
            return null;
        }
    }

    /**
     * 对象转成格式化后的json字符串
     */
    public static <T> String toJsonPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String)obj
                : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.error("parse object to string error", e);
            return null;
        }
    }

    /**
     * json字符串转换为对象
     */
    public static <T> T fromJson(String str, Class<T> clazz) {
        if (StringUtils.isBlank(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T)str : objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            log.error("parse string to object error,", e);
            return null;
        }
    }

    /**
     * json字符串转换为复杂的对象
     * 如 List,Map,数组或是带泛型 的对象
     */
    public static <T> T fromJson(String str, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(str) || typeReference == null) {
            return null;
        }
        try {
            return objectMapper.readValue(str, typeReference);
        } catch (Exception e) {
            log.error("parse string to object error,", e);
            return null;
        }
    }

    /**
     * json字符串转换为集合
     */
    @Deprecated
    public static <T> T fromJson(String str, Class<?> collectionClazz, Class<T> elementClazz) {
        if (StringUtils.isBlank(str) || collectionClazz == null || elementClazz == null) {
            return null;
        }
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClazz, elementClazz);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (Exception e) {
            log.error("parse string to collection error,", e);
            return null;
        }
    }
}
