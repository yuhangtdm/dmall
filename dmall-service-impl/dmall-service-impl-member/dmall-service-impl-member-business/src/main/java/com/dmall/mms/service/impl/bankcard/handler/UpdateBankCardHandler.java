package com.dmall.mms.service.impl.bankcard.handler;

import com.dmall.mms.api.dto.bankcard.request.UpdateBankCardRequestDTO;
import com.dmall.mms.service.impl.bankcard.enums.BankCardErrorEnum;
import com.dmall.mms.generator.dataobject.BankCardDO;
import com.dmall.mms.generator.mapper.BankCardMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改会员银行卡处理器
 * @author: created by hang.yu on 2019-12-01 22:56:07
 */
@Component
public class UpdateBankCardHandler extends AbstractCommonHandler<UpdateBankCardRequestDTO, BankCardDO, Long> {

    @Autowired
    private BankCardMapper bankCardMapper;

    @Override
    public BaseResult<Long> validate(UpdateBankCardRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateBankCardRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
