package com.dmall.mms.service.impl.memberhelp.handler;

import com.dmall.mms.api.dto.memberhelp.common.CommonMemberHelpResponseDTO;
import com.dmall.mms.api.dto.memberhelp.request.ListMemberHelpRequestDTO;
import com.dmall.mms.service.impl.memberhelp.enums.MemberHelpErrorEnum;
import com.dmall.mms.generator.dataobject.MemberHelpDO;
import com.dmall.mms.generator.mapper.MemberHelpMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 会员-帮助关系表 帮助对会员有用列表处理器
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Component
public class ListMemberHelpHandler extends AbstractCommonHandler<ListMemberHelpRequestDTO, MemberHelpDO, CommonMemberHelpResponseDTO> {

    @Autowired
    private MemberHelpMapper memberHelpMapper;

    @Override
    public BaseResult<List<CommonMemberHelpResponseDTO>> validate(ListMemberHelpRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonMemberHelpResponseDTO>> processor(ListMemberHelpRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
