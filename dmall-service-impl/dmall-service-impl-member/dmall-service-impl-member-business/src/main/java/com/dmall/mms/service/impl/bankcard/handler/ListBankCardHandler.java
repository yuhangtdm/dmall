package com.dmall.mms.service.impl.bankcard.handler;

import com.dmall.mms.api.dto.bankcard.common.CommonBankCardResponseDTO;
import com.dmall.mms.api.dto.bankcard.request.ListBankCardRequestDTO;
import com.dmall.mms.generator.dataobject.BankCardDO;
import com.dmall.mms.generator.mapper.BankCardMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 会员银行卡列表处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class ListBankCardHandler extends AbstractCommonHandler<ListBankCardRequestDTO, BankCardDO, CommonBankCardResponseDTO> {

    @Autowired
    private BankCardMapper bankCardMapper;

    @Override
    public BaseResult<List<CommonBankCardResponseDTO>> validate(ListBankCardRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonBankCardResponseDTO>> processor(ListBankCardRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
