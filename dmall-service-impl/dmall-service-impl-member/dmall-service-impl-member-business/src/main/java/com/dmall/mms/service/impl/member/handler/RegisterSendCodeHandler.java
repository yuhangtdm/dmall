package com.dmall.mms.service.impl.member.handler;

import cn.hutool.core.util.RandomUtil;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.notify.email.MailServiceImpl;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.enums.MmsErrorEnum;
import com.dmall.mms.generator.dataobject.MemberDO;
import com.dmall.mms.service.support.CacheKeySupport;
import com.dmall.mms.service.support.MemberSupport;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;

/**
 * @description: 注册会员-发送验证码处理器
 * @author: created by hang.yu on 2020/2/23 16:46
 */
@Component
public class RegisterSendCodeHandler extends AbstractCommonHandler<String, MemberDO, Void> {

    @Autowired
    private MailServiceImpl mailServiceImpl;

    @ApolloConfig("sendEmail")
    private Config config;

    @Autowired
    private MemberSupport memberSupport;

    @Autowired
    private CacheKeySupport cacheKeySupport;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String TEMPLATE = "register";

    @Override
    public BaseResult<Void> processor(String email) {
        // 校验是否已注册
        MemberDO memberDO = memberSupport.getByEmail(email);
        if (memberDO != null) {
            return ResultUtil.fail(MmsErrorEnum.EMAIL_EXISTS);
        }
        // 校验是否三分钟内 邮件已发送
        String key = cacheKeySupport.generateRegister(email);
        if (redisTemplate.opsForValue().get(key) != null) {
            return ResultUtil.fail(MmsErrorEnum.EMAIL_SEND);
        }
        Map<String, Object> value = Maps.newHashMap();
        // 生成6位随机数
        String code = RandomUtil.randomString(RandomUtil.BASE_NUMBER, 6);
        value.put("code", code);
        // 发送邮件
        mailServiceImpl.sendHtmlMail(email, config.getProperty("registerSubject", "注册"), TEMPLATE, value);
        // 缓存验证码
        redisTemplate.opsForValue().set(key, code, Duration.ofMinutes(config.getIntProperty("cacheTime", 3)));
        return ResultUtil.success();
    }
}
