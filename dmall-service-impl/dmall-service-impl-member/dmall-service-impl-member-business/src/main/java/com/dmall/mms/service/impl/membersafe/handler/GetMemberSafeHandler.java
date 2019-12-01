package com.dmall.mms.service.impl.membersafe.handler;

import com.dmall.mms.api.dto.membersafe.common.CommonMemberSafeResponseDTO;
import com.dmall.mms.service.impl.membersafe.enums.MemberSafeErrorEnum;
import com.dmall.mms.generator.dataobject.MemberSafeDO;
import com.dmall.mms.generator.mapper.MemberSafeMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询账户安全处理器
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Component
public class GetMemberSafeHandler extends AbstractCommonHandler<Long, MemberSafeDO, CommonMemberSafeResponseDTO> {

    @Autowired
    private MemberSafeMapper memberSafeMapper;

    @Override
    public BaseResult<CommonMemberSafeResponseDTO> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<CommonMemberSafeResponseDTO> processor(Long id) {
        return ResultUtil.success();
    }

}
