package com.dmall.mms.service.impl.memberinvoice.handler;

import com.dmall.mms.api.dto.memberinvoice.request.UpdateMemberInvoiceRequestDTO;
import com.dmall.mms.service.impl.memberinvoice.enums.MemberInvoiceErrorEnum;
import com.dmall.mms.generator.dataobject.MemberInvoiceDO;
import com.dmall.mms.generator.mapper.MemberInvoiceMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改会员发票处理器
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@Component
public class UpdateMemberInvoiceHandler extends AbstractCommonHandler<UpdateMemberInvoiceRequestDTO, MemberInvoiceDO, Long> {

    @Autowired
    private MemberInvoiceMapper memberInvoiceMapper;

    @Override
    public BaseResult<Long> validate(UpdateMemberInvoiceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateMemberInvoiceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
