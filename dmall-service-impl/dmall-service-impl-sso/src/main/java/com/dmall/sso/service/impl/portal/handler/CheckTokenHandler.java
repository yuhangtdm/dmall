package com.dmall.sso.service.impl.portal.handler;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.BasicStatusEnum;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.sso.service.impl.portal.dataobject.MemberDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @description: CheckTokenHandler
 * @author: created by hang.yu on 2020/2/26 12:39
 */
@Component
public class CheckTokenHandler extends AbstractCommonHandler<String, MemberDO, PortalMemberDTO> {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public BaseResult<PortalMemberDTO> processor(String token) {
        PortalMemberDTO portalMemberDTO =
            (PortalMemberDTO)redisTemplate.opsForValue().get(StrUtil.format("portal_{}", token));
        if (portalMemberDTO == null) {
            return ResultUtil.fail(BasicStatusEnum.USER_NOT_LOGIN);
        }
        return ResultUtil.success(portalMemberDTO);
    }
}
