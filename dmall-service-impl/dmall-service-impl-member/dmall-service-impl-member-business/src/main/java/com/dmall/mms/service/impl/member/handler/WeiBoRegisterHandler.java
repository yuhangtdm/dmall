package com.dmall.mms.service.impl.member.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.portal.PortalMemberDTO;
import com.dmall.common.util.BeanUtil;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.dto.member.enums.GenderEnum;
import com.dmall.mms.api.dto.member.enums.SourceTypeEnum;
import com.dmall.mms.api.dto.member.request.WeiBoLoginRequestDTO;
import com.dmall.mms.generator.dataobject.MemberDO;
import com.dmall.mms.generator.mapper.MemberMapper;
import com.dmall.mms.service.impl.support.MemberSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 微博登录处理器
 * @author: created by hang.yu on 2020/3/1 21:34
 */
@Component
public class WeiBoRegisterHandler extends AbstractCommonHandler<WeiBoLoginRequestDTO, MemberDO, PortalMemberDTO> {

    @Autowired
    private MemberSupport memberSupport;

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public BaseResult<PortalMemberDTO> processor(WeiBoLoginRequestDTO requestDTO) {

        // 根据微博id查询有无会员记录
        MemberDO byWeiBoId = memberSupport.getByWeiBoNo(requestDTO.getWeiBoNo());
        if (byWeiBoId == null) {
            // 注册用户
            MemberDO memberDO = new MemberDO();
            memberDO.setMemberName(requestDTO.getName());
            memberDO.setNickName(requestDTO.getNickName());
            memberDO.setGender(EnumUtil.getData(GenderEnum.class, requestDTO.getIcon()));
            memberDO.setIcon(requestDTO.getIcon());
            memberDO.setSourceType(SourceTypeEnum.WEIBO.getCode());
            memberDO.setWeiBoNo(requestDTO.getWeiBoNo());
            memberMapper.insert(memberDO);
            memberDO.setCreator(memberDO.getId());
            memberDO.setModifier(memberDO.getId());
            memberMapper.updateById(memberDO);
            return ResultUtil.success(BeanUtil.copyProperties(memberDO, PortalMemberDTO.class));
        }
        return ResultUtil.success(BeanUtil.copyProperties(byWeiBoId, PortalMemberDTO.class));
    }
}
