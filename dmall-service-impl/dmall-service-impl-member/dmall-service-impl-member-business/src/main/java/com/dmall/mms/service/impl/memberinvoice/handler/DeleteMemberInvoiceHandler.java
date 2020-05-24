package com.dmall.mms.service.impl.memberinvoice.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.mms.api.enums.MmsErrorEnum;
import com.dmall.mms.generator.dataobject.MemberInvoiceDO;
import com.dmall.mms.generator.mapper.MemberInvoiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 删除会员发票处理器
 * @author: created by hang.yu on 2020/3/28 10:23
 */
@Component
public class DeleteMemberInvoiceHandler extends AbstractCommonHandler<Long, MemberInvoiceDO, Long> {

    @Autowired
    private MemberInvoiceMapper memberInvoiceMapper;

    @Override
    public BaseResult<Long> processor(Long id) {
        MemberInvoiceDO memberInvoiceDO = memberInvoiceMapper.selectById(id);
        if (memberInvoiceDO == null) {
            return ResultUtil.fail(MmsErrorEnum.INVOICE_NOT_EXISTS);
        }
        memberInvoiceMapper.deleteById(id);
        return ResultUtil.success(id);
    }
}
