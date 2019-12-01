package com.dmall.mms.service.impl.bankcard;

import com.dmall.mms.api.dto.bankcard.request.SaveBankCardRequestDTO;
import com.dmall.mms.api.dto.bankcard.request.UpdateBankCardRequestDTO;
import com.dmall.mms.api.dto.bankcard.request.ListBankCardRequestDTO;
import com.dmall.mms.api.dto.bankcard.request.PageBankCardRequestDTO;
import com.dmall.mms.api.dto.bankcard.common.CommonBankCardResponseDTO;
import com.dmall.mms.api.service.BankCardService;
import com.dmall.mms.service.impl.bankcard.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员银行卡服务实现
 * @author: created by hang.yu on 2019-12-01 22:56:07
 */
@RestController
public class  BankCardServiceImpl implements BankCardService {

    @Autowired
    protected SaveBankCardHandler saveBankCardHandler;

    @Autowired
    private DeleteBankCardHandler deleteBankCardHandler;

    @Autowired
    private UpdateBankCardHandler updateBankCardHandler;

    @Autowired
    private GetBankCardHandler getBankCardHandler;

    @Autowired
    private ListBankCardHandler listBankCardHandler;

    @Autowired
    private PageBankCardHandler pageBankCardHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveBankCardRequestDTO requestDTO) {
        return saveBankCardHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteBankCardHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateBankCardRequestDTO requestDTO) {
        return updateBankCardHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonBankCardResponseDTO> get(Long id) {
        return getBankCardHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonBankCardResponseDTO>> list(@RequestBody ListBankCardRequestDTO requestDTO) {
        return listBankCardHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<CommonBankCardResponseDTO>> page(@RequestBody PageBankCardRequestDTO requestDTO) {
        return pageBankCardHandler.handler(requestDTO);
    }

}
