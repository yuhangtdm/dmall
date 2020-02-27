package com.dmall.mms.service.impl.bankcard.handler;

import com.dmall.mms.service.impl.bankcard.enums.BankCardErrorEnum;
import com.dmall.mms.generator.dataobject.BankCardDO;
import com.dmall.mms.generator.mapper.BankCardMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 删除会员银行卡处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class DeleteBankCardHandler extends AbstractCommonHandler<Long, BankCardDO, Long> {

    @Autowired
    private BankCardMapper bankCardMapper;

    @Override
    public BaseResult<Long> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        return ResultUtil.success();
    }

}
