package com.dmall.mms.service.impl.memberviewsku.handler;

import com.dmall.mms.api.dto.memberviewsku.common.CommonMemberViewSkuResponseDTO;
import com.dmall.mms.api.dto.memberviewsku.request.PageMemberViewSkuRequestDTO;
import com.dmall.mms.service.impl.memberviewsku.enums.MemberViewSkuErrorEnum;
import com.dmall.mms.generator.dataobject.MemberViewSkuDO;
import com.dmall.mms.generator.mapper.MemberViewSkuMapper;
import com.dmall.common.model.result.LayuiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 会员浏览历史记录分页处理器
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Component
public class PageMemberViewSkuHandler extends AbstractCommonHandler<PageMemberViewSkuRequestDTO, MemberViewSkuDO, CommonMemberViewSkuResponseDTO> {

    @Autowired
    private MemberViewSkuMapper memberViewSkuMapper;

    @Override
    public BaseResult<LayuiPage<CommonMemberViewSkuResponseDTO>> validate(PageMemberViewSkuRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<CommonMemberViewSkuResponseDTO>> processor(PageMemberViewSkuRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
