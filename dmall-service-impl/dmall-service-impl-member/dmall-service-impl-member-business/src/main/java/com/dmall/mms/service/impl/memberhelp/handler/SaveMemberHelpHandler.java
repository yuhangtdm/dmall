package com.dmall.mms.service.impl.memberhelp.handler;

import com.dmall.mms.api.dto.memberhelp.request.SaveMemberHelpRequestDTO;
import com.dmall.mms.service.impl.memberhelp.enums.MemberHelpErrorEnum;
import com.dmall.mms.generator.dataobject.MemberHelpDO;
import com.dmall.mms.generator.mapper.MemberHelpMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增会员-帮助关系表 帮助对会员有用处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class SaveMemberHelpHandler extends AbstractCommonHandler<SaveMemberHelpRequestDTO, MemberHelpDO, Long> {

    @Autowired
    private MemberHelpMapper memberHelpMapper;

    @Override
    public BaseResult<Long> validate(SaveMemberHelpRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveMemberHelpRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
