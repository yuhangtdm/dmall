package com.dmall.mms.service.impl.memberinvoice.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.dto.memberinvoice.SaveMemberInvoiceRequestDTO;
import com.dmall.mms.generator.dataobject.MemberInvoiceDO;
import com.dmall.mms.generator.mapper.MemberInvoiceMapper;
import com.dmall.mms.service.validate.MemberInvoiceValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增会员发票
 * @author: created by hang.yu on 2020/3/28 10:23
 */
@Component
public class SaveMemberInvoiceHandler extends AbstractCommonHandler<SaveMemberInvoiceRequestDTO, MemberInvoiceDO, Long> {

    @Autowired
    private MemberInvoiceMapper memberInvoiceMapper;

    @Override
    public BaseResult<Long> validate(SaveMemberInvoiceRequestDTO requestDTO) {
        return MemberInvoiceValidate.validate(requestDTO.getInvoiceHeader(), requestDTO.getPersonalName(),
                requestDTO.getCompanyName(), requestDTO.getCustomerTaxNumber());
    }

    @Override
    public BaseResult<Long> processor(SaveMemberInvoiceRequestDTO requestDTO) {
        MemberInvoiceDO memberInvoiceDO = dtoConvertDo(requestDTO, MemberInvoiceDO.class);
        memberInvoiceMapper.insert(memberInvoiceDO);
        return ResultUtil.success(memberInvoiceDO.getId());
    }
}
