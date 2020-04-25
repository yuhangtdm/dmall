package com.dmall.sso.service.impl.portal.handler;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.common.constants.Constants;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.RequestUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.sso.api.enums.LoginTypeEnum;
import com.dmall.sso.api.dto.portal.PortalLoginResponseDTO;
import com.dmall.sso.service.impl.SsoProperties;
import com.dmall.sso.service.impl.portal.dataobject.MemberDO;
import com.dmall.sso.service.impl.portal.dataobject.MemberLoginLogDO;
import com.dmall.sso.service.impl.portal.mapper.MemberLoginLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @description: LoginHandler
 * @author: created by hang.yu on 2020/2/26 12:27
 */
@Component
public class WeiBoLoginHandler extends AbstractCommonHandler<PortalMemberDTO, MemberDO, PortalLoginResponseDTO> {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private SsoProperties ssoProperties;

    @Autowired
    private MemberLoginLogMapper memberLoginLogMapper;

    @Override
    public BaseResult<PortalLoginResponseDTO> processor(PortalMemberDTO requestDTO) {

        // 生成token 并缓存
        String token = IdUtil.simpleUUID();
        requestDTO.setSource(Constants.PORTAL_MEMBER);
        requestDTO.setToken(token);

        // 设置缓存
        redisTemplate.opsForValue().set(StrUtil.format("portal_{}", token), requestDTO, ssoProperties.getPortalTtlDay(), TimeUnit.DAYS);
        // 插入登录日志
        insertLoginLog(requestDTO);

        // 返回
        PortalLoginResponseDTO portalLoginResponseDTO = new PortalLoginResponseDTO();
        portalLoginResponseDTO.setToken(token);
        portalLoginResponseDTO.setUrl(ssoProperties.getPortalSuccessUrl());

        return ResultUtil.success(portalLoginResponseDTO);
    }

    private void insertLoginLog(PortalMemberDTO requestDTO) {
        MemberLoginLogDO memberLoginLogDO = new MemberLoginLogDO();
        memberLoginLogDO.setMemberId(requestDTO.getId());
        memberLoginLogDO.setToken(requestDTO.getToken());
        memberLoginLogDO.setIp(RequestUtil.getIpAddress(RequestUtil.getRequest()));
        memberLoginLogDO.setType(LoginTypeEnum.PC.getCode());
        memberLoginLogMapper.insert(memberLoginLogDO);
    }
}
