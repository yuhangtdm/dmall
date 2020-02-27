package com.dmall.mms.service.impl.memberinvoice.handler;

import com.dmall.mms.api.dto.memberinvoice.common.CommonMemberInvoiceResponseDTO;
import com.dmall.mms.api.dto.memberinvoice.request.PageMemberInvoiceRequestDTO;
import com.dmall.mms.generator.dataobject.MemberInvoiceDO;
import com.dmall.mms.generator.mapper.MemberInvoiceMapper;
import com.dmall.common.dto.LayUiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 会员发票分页处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class PageMemberInvoiceHandler extends AbstractCommonHandler<PageMemberInvoiceRequestDTO, MemberInvoiceDO, CommonMemberInvoiceResponseDTO> {

    @Autowired
    private MemberInvoiceMapper memberInvoiceMapper;

    @Override
    public BaseResult<LayUiPage<CommonMemberInvoiceResponseDTO>> validate(PageMemberInvoiceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonMemberInvoiceResponseDTO>> processor(PageMemberInvoiceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
