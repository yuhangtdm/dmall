package com.dmall.mms.service.impl.membercolletionsubject.handler;

import com.dmall.mms.api.dto.membercolletionsubject.common.CommonMemberColletionSubjectResponseDTO;
import com.dmall.mms.api.dto.membercolletionsubject.request.ListMemberColletionSubjectRequestDTO;
import com.dmall.mms.service.impl.membercolletionsubject.enums.MemberColletionSubjectErrorEnum;
import com.dmall.mms.generator.dataobject.MemberColletionSubjectDO;
import com.dmall.mms.generator.mapper.MemberColletionSubjectMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 会员收藏专题表 列表处理器
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Component
public class ListMemberColletionSubjectHandler extends AbstractCommonHandler<ListMemberColletionSubjectRequestDTO, MemberColletionSubjectDO, CommonMemberColletionSubjectResponseDTO> {

    @Autowired
    private MemberColletionSubjectMapper memberColletionSubjectMapper;

    @Override
    public BaseResult<List<CommonMemberColletionSubjectResponseDTO>> validate(ListMemberColletionSubjectRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonMemberColletionSubjectResponseDTO>> processor(ListMemberColletionSubjectRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
