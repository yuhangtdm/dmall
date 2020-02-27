package com.dmall.mms.service.impl.memberinvoice.handler;

import com.dmall.mms.api.dto.memberinvoice.request.SaveMemberInvoiceRequestDTO;
import com.dmall.mms.service.impl.memberinvoice.enums.MemberInvoiceErrorEnum;
import com.dmall.mms.generator.dataobject.MemberInvoiceDO;
import com.dmall.mms.generator.mapper.MemberInvoiceMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增会员发票处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class SaveMemberInvoiceHandler extends AbstractCommonHandler<SaveMemberInvoiceRequestDTO, MemberInvoiceDO, Long> {

    @Autowired
    private MemberInvoiceMapper memberInvoiceMapper;

    @Override
    public BaseResult<Long> validate(SaveMemberInvoiceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveMemberInvoiceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
