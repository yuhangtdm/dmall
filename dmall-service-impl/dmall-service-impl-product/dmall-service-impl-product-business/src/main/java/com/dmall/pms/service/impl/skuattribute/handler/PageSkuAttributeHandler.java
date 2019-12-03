package com.dmall.pms.service.impl.skuattribute.handler;

import com.dmall.pms.api.dto.skuattribute.common.CommonSkuAttributeResponseDTO;
import com.dmall.pms.api.dto.skuattribute.request.PageSkuAttributeRequestDTO;
import com.dmall.pms.generator.dataobject.SkuAttributeDO;
import com.dmall.pms.generator.mapper.SkuAttributeMapper;
import com.dmall.common.model.result.LayuiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: sku属性分页处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class PageSkuAttributeHandler extends AbstractCommonHandler<PageSkuAttributeRequestDTO, SkuAttributeDO, CommonSkuAttributeResponseDTO> {

    @Autowired
    private SkuAttributeMapper skuAttributeMapper;

    @Override
    public BaseResult<LayuiPage<CommonSkuAttributeResponseDTO>> validate(PageSkuAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<CommonSkuAttributeResponseDTO>> processor(PageSkuAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
