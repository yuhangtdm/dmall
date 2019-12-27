package com.dmall.pms.service.impl.product.handler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.product.request.PageProductRequestDTO;
import com.dmall.pms.api.dto.product.response.PageProductResponseDTO;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.service.impl.brand.cache.BrandCacheService;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.impl.product.enums.ProductErrorEnum;
import com.dmall.pms.service.impl.product.mapper.ProductPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 商品分页处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class PageProductHandler extends AbstractCommonHandler<PageProductRequestDTO, ProductDO, PageProductResponseDTO> {

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private BrandCacheService brandCacheService;

    @Autowired
    private ProductPageMapper productPageMapper;

    @Override
    public BaseResult validate(PageProductRequestDTO requestDTO) {
        // 分类id必须存在
        if (requestDTO.getCategoryId() != null) {
            CategoryDO categoryDO = categoryCacheService.selectById(requestDTO.getCategoryId());
            if (categoryDO == null) {
                return ResultUtil.fail(ProductErrorEnum.CATEGORY_NOT_EXISTS);
            }
        }
        // 品牌id必须存在
        if (requestDTO.getBrandId() != null) {
            BrandDO brandDO = brandCacheService.selectById(requestDTO.getBrandId());
            if (brandDO == null) {
                return ResultUtil.fail(ProductErrorEnum.BRAND_NOT_EXISTS);
            }
        }
        // 品牌id必须存在
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<PageProductResponseDTO>> processor(PageProductRequestDTO requestDTO) {
        Page<PageProductResponseDTO> page = new Page<>(requestDTO.getCurrent(), requestDTO.getSize());
        List<PageProductResponseDTO> responseDTOS = productPageMapper.productPage(page, requestDTO);
        return ResultUtil.success(new LayuiPage(page.getTotal(), responseDTOS));
    }

}
