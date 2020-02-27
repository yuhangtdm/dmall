package com.dmall.sso.service.impl.portal.handler;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.sso.service.impl.portal.dataobject.MemberDO;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @description: LogoutHandler
 * @author: created by hang.yu on 2020/2/26 12:36
 */
public class LogoutHandler extends AbstractCommonHandler<String, MemberDO, Void> {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public BaseResult<Void> processor(String token) {
        redisTemplate.delete(Lists.newArrayList(StrUtil.format("portal_{}", token)));
        return ResultUtil.success();
    }
}
