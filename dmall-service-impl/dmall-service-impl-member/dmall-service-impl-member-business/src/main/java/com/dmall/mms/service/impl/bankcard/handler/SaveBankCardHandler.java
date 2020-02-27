package com.dmall.mms.service.impl.bankcard.handler;

import com.dmall.mms.api.dto.bankcard.request.SaveBankCardRequestDTO;
import com.dmall.mms.service.impl.bankcard.enums.BankCardErrorEnum;
import com.dmall.mms.generator.dataobject.BankCardDO;
import com.dmall.mms.generator.mapper.BankCardMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增会员银行卡处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class SaveBankCardHandler extends AbstractCommonHandler<SaveBankCardRequestDTO, BankCardDO, Long> {

    @Autowired
    private BankCardMapper bankCardMapper;

    @Override
    public BaseResult<Long> validate(SaveBankCardRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveBankCardRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
