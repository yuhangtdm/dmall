package com.dmall.mms.service.impl.memberviewsku.handler;

import com.dmall.mms.api.dto.memberviewsku.request.UpdateMemberViewSkuRequestDTO;
import com.dmall.mms.service.impl.memberviewsku.enums.MemberViewSkuErrorEnum;
import com.dmall.mms.generator.dataobject.MemberViewSkuDO;
import com.dmall.mms.generator.mapper.MemberViewSkuMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改会员浏览历史记录处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class UpdateMemberViewSkuHandler extends AbstractCommonHandler<UpdateMemberViewSkuRequestDTO, MemberViewSkuDO, Long> {

    @Autowired
    private MemberViewSkuMapper memberViewSkuMapper;

    @Override
    public BaseResult<Long> validate(UpdateMemberViewSkuRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateMemberViewSkuRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}