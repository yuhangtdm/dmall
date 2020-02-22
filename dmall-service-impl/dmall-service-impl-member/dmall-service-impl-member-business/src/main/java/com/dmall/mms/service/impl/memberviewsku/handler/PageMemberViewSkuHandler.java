package com.dmall.mms.service.impl.memberviewsku.handler;

import com.dmall.mms.api.dto.memberviewsku.common.CommonMemberViewSkuResponseDTO;
import com.dmall.mms.api.dto.memberviewsku.request.PageMemberViewSkuRequestDTO;
import com.dmall.mms.generator.dataobject.MemberViewSkuDO;
import com.dmall.mms.generator.mapper.MemberViewSkuMapper;
import com.dmall.common.dto.LayUiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 会员浏览历史记录分页处理器
 * @author: created by hang.yu on 2020-02-22 23:31:54
 */
@Component
public class PageMemberViewSkuHandler extends AbstractCommonHandler<PageMemberViewSkuRequestDTO, MemberViewSkuDO, CommonMemberViewSkuResponseDTO> {

    @Autowired
    private MemberViewSkuMapper memberViewSkuMapper;

    @Override
    public BaseResult<LayUiPage<CommonMemberViewSkuResponseDTO>> validate(PageMemberViewSkuRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonMemberViewSkuResponseDTO>> processor(PageMemberViewSkuRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
