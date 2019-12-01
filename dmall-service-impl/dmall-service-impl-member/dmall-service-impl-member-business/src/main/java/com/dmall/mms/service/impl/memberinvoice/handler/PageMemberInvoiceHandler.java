package com.dmall.mms.service.impl.memberinvoice.handler;

import com.dmall.mms.api.dto.memberinvoice.common.CommonMemberInvoiceResponseDTO;
import com.dmall.mms.api.dto.memberinvoice.request.PageMemberInvoiceRequestDTO;
import com.dmall.mms.service.impl.memberinvoice.enums.MemberInvoiceErrorEnum;
import com.dmall.mms.generator.dataobject.MemberInvoiceDO;
import com.dmall.mms.generator.mapper.MemberInvoiceMapper;
import com.dmall.common.model.result.LayuiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 会员发票分页处理器
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Component
public class PageMemberInvoiceHandler extends AbstractCommonHandler<PageMemberInvoiceRequestDTO, MemberInvoiceDO, CommonMemberInvoiceResponseDTO> {

    @Autowired
    private MemberInvoiceMapper memberInvoiceMapper;

    @Override
    public BaseResult<LayuiPage<CommonMemberInvoiceResponseDTO>> validate(PageMemberInvoiceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<CommonMemberInvoiceResponseDTO>> processor(PageMemberInvoiceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
