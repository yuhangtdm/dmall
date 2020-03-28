package com.dmall.mms.service.impl.memberinvoice.handler;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.dto.memberinvoice.UpdateMemberInvoiceRequestDTO;
import com.dmall.mms.api.enums.InvoiceHeaderEnum;
import com.dmall.mms.api.enums.MemberInvoiceErrorEnum;
import com.dmall.mms.generator.dataobject.MemberInvoiceDO;
import com.dmall.mms.generator.mapper.MemberInvoiceMapper;
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
            return ResultUtil.fail(MemberInvoiceErrorEnum.INVOICE_NOT_EXISTS);
        }
        if (requestDTO.getInvoiceHeader().equals(InvoiceHeaderEnum.PERSONAL.getCode())) {
            if (StrUtil.isBlank(requestDTO.getPersonalName())) {
                return ResultUtil.fail(MemberInvoiceErrorEnum.PERSONAL_NAME_EMPTY);
            }
        } else {
            if (StrUtil.isBlank(requestDTO.getCompanyName())) {
                return ResultUtil.fail(MemberInvoiceErrorEnum.COMPANY_NAME_EMPTY);
            }
            if (StrUtil.isBlank(requestDTO.getCustomerTaxNumber())) {
                return ResultUtil.fail(MemberInvoiceErrorEnum.CUSTOMER_TAX_NUMBER_EMPTY);
            }
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateMemberInvoiceRequestDTO requestDTO) {
        memberInvoiceMapper.updateById(dtoConvertDo(requestDTO, MemberInvoiceDO.class));
        return ResultUtil.success(requestDTO.getId());
    }
}
