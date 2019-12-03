package com.dmall.pms.service.impl.product.handler;

import com.dmall.pms.api.dto.product.common.CommonProductResponseDTO;
import com.dmall.pms.api.dto.product.request.PageProductRequestDTO;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.common.model.result.LayuiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 商品分页处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class PageProductHandler extends AbstractCommonHandler<PageProductRequestDTO, ProductDO, CommonProductResponseDTO> {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public BaseResult<LayuiPage<CommonProductResponseDTO>> validate(PageProductRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<CommonProductResponseDTO>> processor(PageProductRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
