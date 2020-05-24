package com.dmall.mms.service.impl.member.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.rbac.shiro.util.PasswordUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.enums.MmsErrorEnum;
import com.dmall.mms.api.enums.SourceTypeEnum;
import com.dmall.mms.api.dto.member.RegisterMemberRequestDTO;
import com.dmall.mms.generator.dataobject.MemberDO;
import com.dmall.mms.generator.mapper.MemberMapper;
import com.dmall.mms.service.support.MemberSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 注册会员
 * @author: created by hang.yu on 2020/2/23 22:00
 */
@Component
public class RegisterMemberHandler extends AbstractCommonHandler<RegisterMemberRequestDTO, MemberDO, Long> {

    @Autowired
    private MemberSupport memberSupport;

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public BaseResult<Long> validate(RegisterMemberRequestDTO requestDTO) {

        // 邮箱必须唯一
        MemberDO byEmail = memberSupport.getByEmail(requestDTO.getEmail());
        if (byEmail != null) {
            return ResultUtil.fail(MmsErrorEnum.EMAIL_EXISTS);
        }
        // 手机号必须唯一
        MemberDO byPhone = memberSupport.getByPhone(requestDTO.getPhone());
        if (byPhone != null) {
            return ResultUtil.fail(MmsErrorEnum.REGISTER_PHONE_EXISTS);
        }
        // 会员名称必须唯一
        MemberDO byName = memberSupport.getByName(requestDTO.getMemberName());
        if (byName != null) {
            return ResultUtil.fail(MmsErrorEnum.REGISTER_NAME_EXISTS);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(RegisterMemberRequestDTO requestDTO) {
        MemberDO memberDO = dtoConvertDo(requestDTO, MemberDO.class);
        // 设置密码
        String password = PasswordUtil.getPassword(requestDTO.getEmail(), requestDTO.getPassword());
        memberDO.setPassword(password);
        memberDO.setSourceType(SourceTypeEnum.REGISTER.getCode());
        memberMapper.insert(memberDO);
        memberDO.setCreator(memberDO.getId());
        memberDO.setModifier(memberDO.getId());
        memberMapper.updateById(memberDO);
        return ResultUtil.success(memberDO.getId());
    }
}
