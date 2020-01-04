package com.dmall.pms.service.impl.product.handler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.product.request.PageProductRequestDTO;
import com.dmall.pms.api.dto.product.response.PageProductResponseDTO;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.service.impl.brand.cache.BrandCacheService;
import com.dmall.pms.service.impl.product.common.ProductValidate;
import com.dmall.pms.service.impl.product.mapper.ProductPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 商品分页处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class PageProductHandler extends AbstractCommonHandler<PageProductRequestDTO, ProductDO, PageProductResponseDTO> {

    @Autowired
    private BrandCacheService brandCacheService;

    @Autowired
    private ProductPageMapper productPageMapper;

    @Autowired
    private ProductValidate productValidate;

    @Override
    public BaseResult validate(PageProductRequestDTO requestDTO) {
        return productValidate.basicValidate(requestDTO.getBrandId(), requestDTO.getCategoryId());
    }

    @Override
    public BaseResult<LayuiPage<PageProductResponseDTO>> processor(PageProductRequestDTO requestDTO) {
        Page<PageProductResponseDTO> page = new Page<>(requestDTO.getCurrent(), requestDTO.getSize());
        List<PageProductResponseDTO> responseDTOS = productPageMapper.productPage(page, requestDTO).stream()
                .map(productDO -> doConvertDto(productDO, PageProductResponseDTO.class)).collect(Collectors.toList());
        return ResultUtil.success(new LayuiPage(page.getTotal(), responseDTOS));
    }

    @Override
    protected void customerConvertDto(PageProductResponseDTO result, ProductDO doo) {
        result.setBrandName(brandCacheService.selectById(doo.getBrandId()).getName());
    }
}
