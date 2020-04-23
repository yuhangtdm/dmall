package com.dmall.mms.service.impl.memberinvoice.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.dto.memberinvoice.UpdateMemberInvoiceRequestDTO;
import com.dmall.mms.api.enums.MmsErrorEnum;
import com.dmall.mms.generator.dataobject.MemberInvoiceDO;
import com.dmall.mms.generator.mapper.MemberInvoiceMapper;
import com.dmall.mms.service.validate.MemberInvoiceValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改发票处理器
 * @author: created by hang.yu on 2020/3/28 10:23
 */
@Component
public class UpdateMemberInvoiceHandler extends AbstractCommonHandler<UpdateMemberInvoiceRequestDTO, MemberInvoiceDO, Long> {

    @Autowired
    private MemberInvoiceMapper memberInvoiceMapper;

    @Override
    public BaseResult<Long> validate(UpdateMemberInvoiceRequestDTO requestDTO) {
        MemberInvoiceDO memberInvoiceDO = memberInvoiceMapper.selectById(requestDTO.getId());
        if (memberInvoiceDO == null) {
            return ResultUtil.fail(MmsErrorEnum.INVOICE_NOT_EXISTS);
        }

        return MemberInvoiceValidate.validate(requestDTO.getInvoiceHeader(),requestDTO.getPersonalName(),
                requestDTO.getCompanyName(),  requestDTO.getCustomerTaxNumber() );
    }

    @Override
    public BaseResult<Long> processor(UpdateMemberInvoiceRequestDTO requestDTO) {
        memberInvoiceMapper.updateById(dtoConvertDo(requestDTO, MemberInvoiceDO.class));
        return ResultUtil.success(requestDTO.getId());
    }
}
