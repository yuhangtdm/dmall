package com.dmall.mms.service.impl.memberhelp.handler;

import com.dmall.mms.api.dto.memberhelp.common.CommonMemberHelpResponseDTO;
import com.dmall.mms.api.dto.memberhelp.request.PageMemberHelpRequestDTO;
import com.dmall.mms.generator.dataobject.MemberHelpDO;
import com.dmall.mms.generator.mapper.MemberHelpMapper;
import com.dmall.common.dto.LayUiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 会员-帮助关系表 帮助对会员有用分页处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class PageMemberHelpHandler extends AbstractCommonHandler<PageMemberHelpRequestDTO, MemberHelpDO, CommonMemberHelpResponseDTO> {

    @Autowired
    private MemberHelpMapper memberHelpMapper;

    @Override
    public BaseResult<LayUiPage<CommonMemberHelpResponseDTO>> validate(PageMemberHelpRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonMemberHelpResponseDTO>> processor(PageMemberHelpRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
