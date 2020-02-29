package com.dmall.mms.service.impl.member.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberContextHolder;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.rbac.shiro.util.PasswordUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.dto.member.request.UpdatePasswordRequestDTO;
import com.dmall.mms.feign.PortalLoginServiceFeign;
import com.dmall.mms.generator.dataobject.MemberDO;
import com.dmall.mms.generator.mapper.MemberMapper;
import com.dmall.mms.api.enums.MemberErrorEnum;
import com.dmall.sso.api.dto.portal.PortalLoginRequestDTO;
import com.dmall.sso.api.dto.portal.PortalLoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: UpdatePasswordHandler
 * @author: created by hang.yu on 2020/2/27 23:16
 */
@Component
public class UpdatePasswordHandler extends AbstractCommonHandler<UpdatePasswordRequestDTO, MemberDO, String> {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private PortalLoginServiceFeign portalLoginServiceFeign;

    @Override
    public BaseResult<String> processor(UpdatePasswordRequestDTO requestDTO) {
        // 获取当前登录用户
        PortalMemberDTO loginMember = PortalMemberContextHolder.get();

        // 判断是否是本人修改
        if (!requestDTO.getEmail().equals(loginMember.getEmail())) {
            return ResultUtil.fail(MemberErrorEnum.EMAIL_ERROR);
        }
        // 判断原密码是否正确
        if (!requestDTO.getOldPassword().equals(loginMember.getPassword())) {
            return ResultUtil.fail(MemberErrorEnum.PASSWORD_ERROR);
        }

        // 修改新密码
        MemberDO memberDO = new MemberDO();
        memberDO.setId(loginMember.getId());
        memberDO.setPassword(PasswordUtil.getPassword(loginMember.getEmail(), requestDTO.getNewPassword()));
        memberMapper.updateById(memberDO);

        // 调用sso退出登录
        portalLoginServiceFeign.logout(loginMember.getToken());
        // 调用sso重新登录
        PortalLoginRequestDTO loginRequestDTO = new PortalLoginRequestDTO();
        loginRequestDTO.setMemberName(loginMember.getMemberName());
        loginRequestDTO.setPassword(requestDTO.getNewPassword());
        BaseResult<PortalLoginResponseDTO> login = portalLoginServiceFeign.login(loginRequestDTO);

        return ResultUtil.success(login.getData().getToken());
    }
}
