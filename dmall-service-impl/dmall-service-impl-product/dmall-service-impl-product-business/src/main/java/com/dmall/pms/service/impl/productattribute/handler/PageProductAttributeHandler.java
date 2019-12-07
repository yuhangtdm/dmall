package com.dmall.pms.service.impl.productattribute.handler;

import com.dmall.pms.api.dto.productattribute.common.CommonProductAttributeResponseDTO;
import com.dmall.pms.api.dto.productattribute.request.PageProductAttributeRequestDTO;
import com.dmall.pms.generator.dataobject.ProductAttributeDO;
import com.dmall.pms.generator.mapper.ProductAttributeMapper;
import com.dmall.common.model.result.LayuiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 属性值分页处理器
 * @author: created by hang.yu on 2019-12-07 21:04:46
 */
@Component
public class PageProductAttributeHandler extends AbstractCommonHandler<PageProductAttributeRequestDTO, ProductAttributeDO, CommonProductAttributeResponseDTO> {

    @Autowired
    private ProductAttributeMapper productAttributeMapper;

    @Override
    public BaseResult<LayuiPage<CommonProductAttributeResponseDTO>> validate(PageProductAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<CommonProductAttributeResponseDTO>> processor(PageProductAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
