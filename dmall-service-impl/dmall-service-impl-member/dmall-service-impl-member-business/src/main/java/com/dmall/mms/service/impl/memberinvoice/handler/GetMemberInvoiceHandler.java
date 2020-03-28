package com.dmall.mms.service.impl.memberinvoice.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.dto.memberinvoice.GetMemberInvoiceResponseDTO;
import com.dmall.mms.api.enums.MemberInvoiceErrorEnum;
import com.dmall.mms.generator.dataobject.MemberInvoiceDO;
import com.dmall.mms.generator.mapper.MemberInvoiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 获取发票处理器
 * @author: created by hang.yu on 2020/3/28 10:23
 */
@Component
public class GetMemberInvoiceHandler extends AbstractCommonHandler<Long, MemberInvoiceDO, GetMemberInvoiceResponseDTO> {

    @Autowired
    private MemberInvoiceMapper memberInvoiceMapper;

    @Override
    public BaseResult<GetMemberInvoiceResponseDTO> processor(Long id) {
        MemberInvoiceDO memberInvoiceDO = memberInvoiceMapper.selectById(id);
        if (memberInvoiceDO == null) {
            return ResultUtil.fail(MemberInvoiceErrorEnum.INVOICE_NOT_EXISTS);
        }
        return ResultUtil.success(doConvertDto(memberInvoiceDO, GetMemberInvoiceResponseDTO.class));
    }
}
