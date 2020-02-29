package com.dmall.sso.service.impl.portal.handler;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.common.constants.Constants;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.RequestUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.sso.api.dto.portal.LoginTypeEnum;
import com.dmall.sso.api.dto.portal.PortalLoginRequestDTO;
import com.dmall.sso.api.dto.portal.PortalLoginResponseDTO;
import com.dmall.sso.service.impl.SsoProperties;
import com.dmall.sso.service.impl.portal.PasswordUtil;
import com.dmall.sso.api.enums.PortalLoginErrorEnum;
import com.dmall.sso.service.impl.portal.dataobject.MemberDO;
import com.dmall.sso.service.impl.portal.dataobject.MemberLoginLogDO;
import com.dmall.sso.service.impl.portal.mapper.MemberLoginLogMapper;
import com.dmall.sso.service.impl.portal.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @description: LoginHandler
 * @author: created by hang.yu on 2020/2/26 12:27
 */
@Component
public class LoginHandler extends AbstractCommonHandler<PortalLoginRequestDTO, MemberDO, PortalLoginResponseDTO> {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private SsoProperties ssoProperties;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberLoginLogMapper memberLoginLogMapper;

    @Override
    public BaseResult<PortalLoginResponseDTO> processor(PortalLoginRequestDTO requestDTO) {
        MemberDO memberDO = memberMapper.login(requestDTO.getMemberName());
        if (memberDO == null) {
            return ResultUtil.fail(PortalLoginErrorEnum.MEMBER_NAME_INCORRECT);
        }
        // 比较密码
        if (!PasswordUtil.checkPassword(memberDO.getEmail(),requestDTO.getPassword(), memberDO.getPassword())){
            return ResultUtil.fail(PortalLoginErrorEnum.PASSWORD_INCORRECT);
        }
        if (YNEnum.Y.getCode().equals(memberDO.getIsDeleted())) {
            return ResultUtil.fail(PortalLoginErrorEnum.USER_INVALID);
        }

        // 生成token 并缓存
        String token = IdUtil.simpleUUID();
        PortalMemberDTO portalMemberDTO = new PortalMemberDTO();
        portalMemberDTO.setId(memberDO.getId());
        portalMemberDTO.setMemberName(memberDO.getMemberName());
        portalMemberDTO.setNickName(memberDO.getNickName());
        portalMemberDTO.setPhone(memberDO.getPhone());
        portalMemberDTO.setEmail(memberDO.getEmail());
        portalMemberDTO.setWechatNo(memberDO.getWechatNo());
        portalMemberDTO.setRealName(memberDO.getRealName());
        portalMemberDTO.setSource(Constants.PORTAL_MEMBER);
        portalMemberDTO.setPassword(memberDO.getPassword());
        portalMemberDTO.setToken(token);

        // 设置缓存
        redisTemplate.opsForValue().set(StrUtil.format("portal_{}", token), portalMemberDTO, ssoProperties.getPortalTtlDay(), TimeUnit.DAYS);
        // 插入登录日志
        insertLoginLog(memberDO, token);

        PortalLoginResponseDTO portalLoginResponseDTO = new PortalLoginResponseDTO();
        portalLoginResponseDTO.setToken(token);
        portalLoginResponseDTO.setUrl(ssoProperties.getPortalSuccessUrl());

        return ResultUtil.success(portalLoginResponseDTO);
    }


    private void insertLoginLog(MemberDO memberDO, String token){
        MemberLoginLogDO memberLoginLogDO = new MemberLoginLogDO();
        memberLoginLogDO.setMemberId(memberDO.getId());
        memberLoginLogDO.setToken(token);
        memberLoginLogDO.setIp(RequestUtil.getIpAddress(RequestUtil.getRequest()));
        memberLoginLogDO.setType(LoginTypeEnum.PC.getCode());
        memberLoginLogMapper.insert(memberLoginLogDO);
    }
}
