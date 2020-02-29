package com.dmall.mms.service.impl.member.handler;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.rbac.shiro.util.PasswordUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.dto.member.request.ResetPasswordRequestDTO;
import com.dmall.mms.feign.PortalLoginServiceFeign;
import com.dmall.mms.generator.dataobject.MemberDO;
import com.dmall.mms.api.enums.MemberErrorEnum;
import com.dmall.mms.service.impl.support.CacheKeySupport;
import com.dmall.sso.api.dto.portal.PortalLoginRequestDTO;
import com.dmall.sso.api.dto.portal.PortalLoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @description: 重新设置密码处理器
 * @author: created by hang.yu on 2020/2/23 21:47
 */
@Component
public class ResetPasswordHandler extends AbstractCommonHandler<ResetPasswordRequestDTO, MemberDO, String> {

    @Autowired
    private CacheKeySupport cacheKeySupport;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private PortalLoginServiceFeign portalLoginServiceFeign;

    @Override
    public BaseResult<String> processor(ResetPasswordRequestDTO requestDTO) {
        // 验证码不能过期
        String key = cacheKeySupport.generateForgetPassword(requestDTO.getEmail());
        String code = (String) redisTemplate.opsForValue().get(key);
        if (StrUtil.isBlank(code)){
            return ResultUtil.fail(MemberErrorEnum.CHECK_CODE_EXPIRED);
        }
        // 验证码正确
        if (!code.equals(requestDTO.getCode())){
            return ResultUtil.fail(MemberErrorEnum.CHECK_CODE_ERROR);
        }

        // 修改新密码
        PortalMemberDTO loginMember = PortalMemberContextHolder.get();
        MemberDO memberDO = new MemberDO();
        memberDO.setId(loginMember.getId());
        memberDO.setPassword(PasswordUtil.getPassword(loginMember.getEmail(), requestDTO.getNewPassword()));

        // 退出登录
        portalLoginServiceFeign.logout(loginMember.getToken());
        // 重新登录
        PortalLoginRequestDTO loginRequestDTO = new PortalLoginRequestDTO();
        loginRequestDTO.setMemberName(loginMember.getMemberName());
        loginRequestDTO.setPassword(loginMember.getPassword());
        BaseResult<PortalLoginResponseDTO> login = portalLoginServiceFeign.login(loginRequestDTO);

        return ResultUtil.success(login.getData().getToken());
    }
}
