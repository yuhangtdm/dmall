package com.dmall.mms.service.impl.member.handler;

import cn.hutool.core.util.RandomUtil;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.notify.email.MailServiceImpl;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.generator.dataobject.MemberDO;
import com.dmall.mms.api.enums.MemberErrorEnum;
import com.dmall.mms.service.impl.support.CacheKeySupport;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;

/**
 * @description: 忘记密码-发送验证码处理器
 * @author: created by hang.yu on 2020/2/23 16:46
 */
@Component
public class ForgetPasswordSendCodeHandler extends AbstractCommonHandler<String, MemberDO, Long> {

    @Autowired
    private MailServiceImpl mailServiceImpl;

    @ApolloConfig("sendEmail")
    private Config config;

    @Autowired
    private CacheKeySupport cacheKeySupport;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String TEMPLATE = "forgetPassword";

    @Override
    public BaseResult<Long> processor(String email) {
        // 获取当前登录用户
        PortalMemberDTO loginMember = PortalMemberContextHolder.get();
        // 校验是否三分钟内 邮件已发送
        String key = cacheKeySupport.generateForgetPassword(email);
        if (redisTemplate.opsForValue().get(key) != null) {
            return ResultUtil.fail(MemberErrorEnum.EMAIL_SEND);
        }
        Map<String, Object> value = Maps.newHashMap();
        // 生成6位随机数
        String code = RandomUtil.randomString(RandomUtil.BASE_NUMBER, 6);
        value.put("code", code);
        // 发送邮件
        mailServiceImpl.sendHtmlMail(email, config.getProperty("forgetPasswordSubject", "忘记密码"),
                TEMPLATE, value);
        // 缓存验证码
        redisTemplate.opsForValue().set(key, code, Duration.ofMinutes(config.getIntProperty("cacheTime", 3)));
        return ResultUtil.success(loginMember.getId());
    }
}
