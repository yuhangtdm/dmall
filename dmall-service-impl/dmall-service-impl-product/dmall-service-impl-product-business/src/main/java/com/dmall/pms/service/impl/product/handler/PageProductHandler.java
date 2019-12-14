package com.dmall.pms.service.impl.product.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.service.impl.brand.cache.BrandCacheService;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.impl.category.support.CategorySupport;
import com.dmall.pms.service.impl.product.enums.ProductErrorEnum;
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
    private ProductMapper productMapper;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private BrandCacheService brandCacheService;

    @Autowired
    private CategorySupport categorySupport;

    @Override
    public BaseResult validate(PageProductRequestDTO requestDTO) {
        // 分类id必须存在
        if (requestDTO.getCategoryId() != null){
            CategoryDO categoryDO = categoryCacheService.selectById(requestDTO.getCategoryId());
            if (categoryDO == null){
                return ResultUtil.fail(ProductErrorEnum.CATEGORY_NOT_EXISTS);
            }
        }

        if (requestDTO.getBrandId() != null){
            BrandDO brandDO = brandCacheService.selectById(requestDTO.getBrandId());
            if (brandDO == null){
                return ResultUtil.fail(ProductErrorEnum.BRAND_NOT_EXISTS);
            }
        }

        // 品牌id必须存在
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<PageProductResponseDTO>> processor(PageProductRequestDTO requestDTO) {
        LambdaQueryWrapper<ProductDO> wrapper = Wrappers.<ProductDO>lambdaQuery()
                .eq(requestDTO.getBrandId() != null, ProductDO::getBrandId, requestDTO.getBrandId())
                .like(StrUtil.isNotBlank(requestDTO.getProductNo()), ProductDO::getProductNo, requestDTO.getProductNo())
                .like(StrUtil.isNotBlank(requestDTO.getName()), ProductDO::getName, requestDTO.getName())
                .ge(requestDTO.getOnMarketTimeStart() != null, ProductDO::getOnMarketTime, requestDTO.getOnMarketTimeStart())
                .le(requestDTO.getOnMarketTimeEnd() != null , ProductDO::getOnMarketTime, requestDTO.getOnMarketTimeEnd())
                ;
        // 获取分类下的所有子分类
        if (requestDTO.getCategoryId() != null){
            wrapper.in(ProductDO::getCategoryId,categorySupport.getSubLevelIds(requestDTO.getCategoryId()));
        }
        IPage<ProductDO> page = new Page<>(requestDTO.getCurrent(), requestDTO.getSize());
        page = productMapper.selectPage(page, wrapper);
        List<PageProductResponseDTO> responseDTOS = page.getRecords().stream().map(doo -> doConvertDto(doo, PageProductResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(new LayuiPage(page.getTotal(), responseDTOS));
    }

    @Override
    protected void customerConvertDto(PageProductResponseDTO result, ProductDO doo) {
        result.setBrandName(brandCacheService.selectById(doo.getBrandId()).getName());
        result.setCascadeCategoryName(categorySupport.getCascadeCategoryName(doo.getCategoryId()));
    }
}
