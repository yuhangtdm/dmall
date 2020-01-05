package com.dmall.bms.service.impl.merchants.handler;

import com.dmall.bms.api.dto.merchants.common.CommonMerchantsResponseDTO;
import com.dmall.bms.api.dto.merchants.request.PageMerchantsRequestDTO;
import com.dmall.bms.generator.dataobject.MerchantsDO;
import com.dmall.bms.generator.mapper.MerchantsMapper;
import com.dmall.common.model.result.LayuiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 商家店铺表 1期只有一家店铺分页处理器
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@Component
public class PageMerchantsHandler extends AbstractCommonHandler<PageMerchantsRequestDTO, MerchantsDO, CommonMerchantsResponseDTO> {

    @Autowired
    private MerchantsMapper merchantsMapper;

    @Override
    public BaseResult<LayuiPage<CommonMerchantsResponseDTO>> validate(PageMerchantsRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<CommonMerchantsResponseDTO>> processor(PageMerchantsRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
