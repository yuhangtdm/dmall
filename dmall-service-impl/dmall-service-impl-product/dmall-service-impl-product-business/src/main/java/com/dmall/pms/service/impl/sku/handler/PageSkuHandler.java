package com.dmall.pms.service.impl.sku.handler;

import com.dmall.pms.api.dto.sku.common.CommonSkuResponseDTO;
import com.dmall.pms.api.dto.sku.request.PageSkuRequestDTO;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.dmall.common.model.result.LayuiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: sku分页处理器
 * @author: created by hang.yu on 2019-12-16 15:14:50
 */
@Component
public class PageSkuHandler extends AbstractCommonHandler<PageSkuRequestDTO, SkuDO, CommonSkuResponseDTO> {

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public BaseResult<LayuiPage<CommonSkuResponseDTO>> validate(PageSkuRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<CommonSkuResponseDTO>> processor(PageSkuRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
