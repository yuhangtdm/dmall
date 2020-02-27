package com.dmall.mms.service.impl.member.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.dto.member.request.ForgetPasswordRequestDTO;
import com.dmall.mms.generator.dataobject.MemberDO;
import com.dmall.mms.service.impl.member.enums.MemberErrorEnum;
import com.dmall.mms.service.impl.support.CacheKeySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @description: ForgetPasswordHandler
 * @author: created by hang.yu on 2020/2/23 21:47
 */
@Component
public class ForgetPasswordHandler extends AbstractCommonHandler<ForgetPasswordRequestDTO, MemberDO, String> {

    @Autowired
    private CacheKeySupport cacheKeySupport;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 校验验证码
     */
    public BaseResult<String> checkCode(String email, String code) {
        String key = cacheKeySupport.generateForgetPassword( email);
        Object o = redisTemplate.opsForValue().get(key);
        if (o == null) {
            return ResultUtil.fail(MemberErrorEnum.CHECK_CODE_EXPIRED);
        }
        if (!Objects.equals(o, code)){
            return ResultUtil.fail(MemberErrorEnum.CHECK_CODE_ERROR);
        }


        return ResultUtil.success(email);
    }

    @Override
    public BaseResult<String> processor(ForgetPasswordRequestDTO forgetPasswordRequestDTO) {
        // 验证码不能过期

        // 验证码正确

        // 修改新密码

        // 退出登录

        // 重新登录
        return null;
    }
}
