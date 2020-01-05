package com.dmall.bms.service.impl.merchants.handler;

import com.dmall.bms.api.dto.merchants.common.CommonMerchantsResponseDTO;
import com.dmall.bms.api.dto.merchants.request.ListMerchantsRequestDTO;
import com.dmall.bms.generator.dataobject.MerchantsDO;
import com.dmall.bms.generator.mapper.MerchantsMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 商家店铺表 1期只有一家店铺列表处理器
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@Component
public class ListMerchantsHandler extends AbstractCommonHandler<ListMerchantsRequestDTO, MerchantsDO, CommonMerchantsResponseDTO> {

    @Autowired
    private MerchantsMapper merchantsMapper;

    @Override
    public BaseResult<List<CommonMerchantsResponseDTO>> validate(ListMerchantsRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonMerchantsResponseDTO>> processor(ListMerchantsRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
