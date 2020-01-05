package com.dmall.bms.service.impl.merchants.handler;

import com.dmall.bms.api.dto.merchants.request.SaveMerchantsRequestDTO;
import com.dmall.bms.service.impl.merchants.enums.MerchantsErrorEnum;
import com.dmall.bms.generator.dataobject.MerchantsDO;
import com.dmall.bms.generator.mapper.MerchantsMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增商家店铺表 1期只有一家店铺处理器
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@Component
public class SaveMerchantsHandler extends AbstractCommonHandler<SaveMerchantsRequestDTO, MerchantsDO, Long> {

    @Autowired
    private MerchantsMapper merchantsMapper;

    @Override
    public BaseResult<Long> validate(SaveMerchantsRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveMerchantsRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
