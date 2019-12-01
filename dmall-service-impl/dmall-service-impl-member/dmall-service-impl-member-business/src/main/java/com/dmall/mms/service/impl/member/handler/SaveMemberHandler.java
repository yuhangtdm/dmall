package com.dmall.mms.service.impl.member.handler;

import com.dmall.mms.api.dto.member.request.SaveMemberRequestDTO;
import com.dmall.mms.service.impl.member.enums.MemberErrorEnum;
import com.dmall.mms.generator.dataobject.MemberDO;
import com.dmall.mms.generator.mapper.MemberMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增会员处理器
 * @author: created by hang.yu on 2019-12-01 22:56:07
 */
@Component
public class SaveMemberHandler extends AbstractCommonHandler<SaveMemberRequestDTO, MemberDO, Long> {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public BaseResult<Long> validate(SaveMemberRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveMemberRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
