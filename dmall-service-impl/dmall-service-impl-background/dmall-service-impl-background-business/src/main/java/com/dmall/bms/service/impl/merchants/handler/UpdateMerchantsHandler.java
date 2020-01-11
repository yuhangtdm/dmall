package com.dmall.bms.service.impl.merchants.handler;

import com.dmall.bms.api.dto.merchants.request.UpdateMerchantsRequestDTO;
import com.dmall.bms.generator.dataobject.MerchantsDO;
import com.dmall.bms.generator.mapper.MerchantsMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改商家店铺表 1期只有一家店铺处理器
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@Component
public class UpdateMerchantsHandler extends AbstractCommonHandler<UpdateMerchantsRequestDTO, MerchantsDO, Long> {

    @Autowired
    private MerchantsMapper merchantsMapper;

    @Override
    public BaseResult<Long> validate(UpdateMerchantsRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateMerchantsRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}