package com.dmall.mms.service.impl.support;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description: CacheKeySupport
 * @author: created by hang.yu on 2020/2/23 21:39
 */
@Component
public class CacheKeySupport {

    /**
     * 缓存key
     */
    private static final String REGISTER_KEY = "{}register_Code";

    private static final String FORGET_PASSWORD_KEY = "{}forgetPassword_Code";

    /**
     * 缓存key前缀
     */
    @Value("${dmall.cache.redis.cacheKeyPrefix}")
    private String cacheKeyPrefix;

    public String generate(String key, String uniqueValue) {
        return StrUtil.format("{}:{}_{}", cacheKeyPrefix, key, uniqueValue);
    }

    /**
     * 注册码的key
     */
    public String generateRegister(String email) {
        return generate(REGISTER_KEY, email);
    }

    /**
     * 注册码的key
     */
    public String generateForgetPassword(String email) {
        return generate(FORGET_PASSWORD_KEY, email);
    }

}
