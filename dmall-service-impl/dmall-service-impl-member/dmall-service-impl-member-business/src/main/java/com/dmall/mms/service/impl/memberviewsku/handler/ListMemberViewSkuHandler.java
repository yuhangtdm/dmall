package com.dmall.mms.service.impl.memberviewsku.handler;

import com.dmall.mms.api.dto.memberviewsku.common.CommonMemberViewSkuResponseDTO;
import com.dmall.mms.api.dto.memberviewsku.request.ListMemberViewSkuRequestDTO;
import com.dmall.mms.service.impl.memberviewsku.enums.MemberViewSkuErrorEnum;
import com.dmall.mms.generator.dataobject.MemberViewSkuDO;
import com.dmall.mms.generator.mapper.MemberViewSkuMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 会员浏览历史记录列表处理器
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Component
public class ListMemberViewSkuHandler extends AbstractCommonHandler<ListMemberViewSkuRequestDTO, MemberViewSkuDO, CommonMemberViewSkuResponseDTO> {

    @Autowired
    private MemberViewSkuMapper memberViewSkuMapper;

    @Override
    public BaseResult<List<CommonMemberViewSkuResponseDTO>> validate(ListMemberViewSkuRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonMemberViewSkuResponseDTO>> processor(ListMemberViewSkuRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
