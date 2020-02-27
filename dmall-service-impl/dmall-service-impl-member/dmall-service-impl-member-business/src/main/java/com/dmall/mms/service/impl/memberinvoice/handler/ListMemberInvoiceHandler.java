package com.dmall.mms.service.impl.memberinvoice.handler;

import com.dmall.mms.api.dto.memberinvoice.common.CommonMemberInvoiceResponseDTO;
import com.dmall.mms.api.dto.memberinvoice.request.ListMemberInvoiceRequestDTO;
import com.dmall.mms.generator.dataobject.MemberInvoiceDO;
import com.dmall.mms.generator.mapper.MemberInvoiceMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 会员发票列表处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class ListMemberInvoiceHandler extends AbstractCommonHandler<ListMemberInvoiceRequestDTO, MemberInvoiceDO, CommonMemberInvoiceResponseDTO> {

    @Autowired
    private MemberInvoiceMapper memberInvoiceMapper;

    @Override
    public BaseResult<List<CommonMemberInvoiceResponseDTO>> validate(ListMemberInvoiceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonMemberInvoiceResponseDTO>> processor(ListMemberInvoiceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
