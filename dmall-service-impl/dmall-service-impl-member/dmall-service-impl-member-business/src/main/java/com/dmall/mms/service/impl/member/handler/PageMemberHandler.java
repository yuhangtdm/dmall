package com.dmall.mms.service.impl.member.handler;

import com.dmall.mms.api.dto.member.common.CommonMemberResponseDTO;
import com.dmall.mms.api.dto.member.request.PageMemberRequestDTO;
import com.dmall.mms.service.impl.member.enums.MemberErrorEnum;
import com.dmall.mms.generator.dataobject.MemberDO;
import com.dmall.mms.generator.mapper.MemberMapper;
import com.dmall.common.model.result.LayuiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 会员分页处理器
 * @author: created by hang.yu on 2019-12-01 22:56:07
 */
@Component
public class PageMemberHandler extends AbstractCommonHandler<PageMemberRequestDTO, MemberDO, CommonMemberResponseDTO> {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public BaseResult<LayuiPage<CommonMemberResponseDTO>> validate(PageMemberRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<CommonMemberResponseDTO>> processor(PageMemberRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
