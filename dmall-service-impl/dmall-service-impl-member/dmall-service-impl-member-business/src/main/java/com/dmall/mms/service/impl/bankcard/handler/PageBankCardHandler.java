package com.dmall.mms.service.impl.bankcard.handler;

import com.dmall.mms.api.dto.bankcard.common.CommonBankCardResponseDTO;
import com.dmall.mms.api.dto.bankcard.request.PageBankCardRequestDTO;
import com.dmall.mms.generator.dataobject.BankCardDO;
import com.dmall.mms.generator.mapper.BankCardMapper;
import com.dmall.common.dto.LayUiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 会员银行卡分页处理器
 * @author: created by hang.yu on 2020-02-23 19:41:03
 */
@Component
public class PageBankCardHandler extends AbstractCommonHandler<PageBankCardRequestDTO, BankCardDO, CommonBankCardResponseDTO> {

    @Autowired
    private BankCardMapper bankCardMapper;

    @Override
    public BaseResult<LayUiPage<CommonBankCardResponseDTO>> validate(PageBankCardRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonBankCardResponseDTO>> processor(PageBankCardRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
