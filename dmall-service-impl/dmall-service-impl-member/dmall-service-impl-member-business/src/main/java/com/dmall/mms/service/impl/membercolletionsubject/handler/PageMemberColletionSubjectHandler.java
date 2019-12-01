package com.dmall.mms.service.impl.membercolletionsubject.handler;

import com.dmall.mms.api.dto.membercolletionsubject.common.CommonMemberColletionSubjectResponseDTO;
import com.dmall.mms.api.dto.membercolletionsubject.request.PageMemberColletionSubjectRequestDTO;
import com.dmall.mms.service.impl.membercolletionsubject.enums.MemberColletionSubjectErrorEnum;
import com.dmall.mms.generator.dataobject.MemberColletionSubjectDO;
import com.dmall.mms.generator.mapper.MemberColletionSubjectMapper;
import com.dmall.common.model.result.LayuiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 会员收藏专题表 分页处理器
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Component
public class PageMemberColletionSubjectHandler extends AbstractCommonHandler<PageMemberColletionSubjectRequestDTO, MemberColletionSubjectDO, CommonMemberColletionSubjectResponseDTO> {

    @Autowired
    private MemberColletionSubjectMapper memberColletionSubjectMapper;

    @Override
    public BaseResult<LayuiPage<CommonMemberColletionSubjectResponseDTO>> validate(PageMemberColletionSubjectRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<CommonMemberColletionSubjectResponseDTO>> processor(PageMemberColletionSubjectRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
