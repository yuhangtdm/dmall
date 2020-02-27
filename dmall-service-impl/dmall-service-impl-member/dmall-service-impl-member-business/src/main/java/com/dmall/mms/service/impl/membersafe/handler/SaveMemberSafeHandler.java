package com.dmall.mms.service.impl.membersafe.handler;

import com.dmall.mms.api.dto.membersafe.request.SaveMemberSafeRequestDTO;
import com.dmall.mms.service.impl.membersafe.enums.MemberSafeErrorEnum;
import com.dmall.mms.generator.dataobject.MemberSafeDO;
import com.dmall.mms.generator.mapper.MemberSafeMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增账户安全处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class SaveMemberSafeHandler extends AbstractCommonHandler<SaveMemberSafeRequestDTO, MemberSafeDO, Long> {

    @Autowired
    private MemberSafeMapper memberSafeMapper;

    @Override
    public BaseResult<Long> validate(SaveMemberSafeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveMemberSafeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
