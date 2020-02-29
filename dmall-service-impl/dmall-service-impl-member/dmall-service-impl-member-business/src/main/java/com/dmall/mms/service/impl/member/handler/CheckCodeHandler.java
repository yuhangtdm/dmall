package com.dmall.mms.service.impl.member.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.mms.api.enums.MemberErrorEnum;
import com.dmall.mms.service.impl.support.CacheKeySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @description: 校验注册验证码
 * @author: created by hang.yu on 2020/2/23 21:47
 */
@Component
public class CheckCodeHandler {

    @Autowired
    private CacheKeySupport cacheKeySupport;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 校验验证码
     */
    public BaseResult<String> checkCode(String email, String code) {
        String key = cacheKeySupport.generateRegister( email);
        Object o = redisTemplate.opsForValue().get(key);
        if (o == null) {
            return ResultUtil.fail(MemberErrorEnum.CHECK_CODE_EXPIRED);
        }
        if (!Objects.equals(o, code)){
            return ResultUtil.fail(MemberErrorEnum.CHECK_CODE_ERROR);
        }
        return ResultUtil.success(email);
    }
}
