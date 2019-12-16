package com.dmall.pms.service.impl.sku.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.common.enums.base.EnumUtil;
import com.dmall.common.enums.base.YNEnum;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.sku.enums.SkuAuditStatusEnum;
import com.dmall.pms.api.dto.sku.request.PageSkuRequestDTO;
import com.dmall.pms.api.dto.sku.response.PageSkuResponseDTO;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.dmall.pms.service.impl.brand.cache.BrandCacheService;
import com.dmall.pms.service.impl.category.support.CategorySupport;
import com.dmall.pms.service.impl.product.common.ProductValidate;
import com.dmall.pms.service.impl.sku.enums.SkuErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: sku分页处理器
 * @author: created by hang.yu on 2019-12-16 15:14:50
 */
@Component
public class PageSkuHandler extends AbstractCommonHandler<PageSkuRequestDTO, SkuDO, PageSkuResponseDTO> {

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductValidate productValidate;

    @Autowired
    private CategorySupport categorySupport;

    @Autowired
    private BrandCacheService brandCacheService;

    @Override
    public BaseResult<LayuiPage<PageSkuResponseDTO>> validate(PageSkuRequestDTO requestDTO) {
        return productValidate.basicValidate(requestDTO.getCategoryId(), requestDTO.getBrandId());
    }

    @Override
    public BaseResult<LayuiPage<PageSkuResponseDTO>> processor(PageSkuRequestDTO requestDTO) {
        SkuDO skuDO = dtoConvertDo(requestDTO, SkuDO.class);
        if (StrUtil.isNotBlank(requestDTO.getProductNo())) {
            ProductDO productDO = productMapper.selectOne(Wrappers.<ProductDO>lambdaQuery()
                    .eq(ProductDO::getProductNo, requestDTO.getProductNo()));
            if (productDO == null) {
                return ResultUtil.fail(SkuErrorEnum.PRODUCT_NOT_EXISTS);
            }
            skuDO.setProductId(productDO.getId());
        }
        if (StrUtil.isNotBlank(requestDTO.getSkuNo())) {
            SkuDO sku = skuMapper.selectOne(Wrappers.<SkuDO>
                    lambdaQuery().eq(SkuDO::getSkuNo, requestDTO.getSkuNo()));
            if (sku == null) {
                return ResultUtil.fail(SkuErrorEnum.SKU_NOT_EXIST);
            }
            skuDO.setId(sku.getId());
        }
        IPage<SkuDO> page = new Page(requestDTO.getCurrent(), requestDTO.getSize());
        page = skuMapper.selectPage(page, Wrappers.query(skuDO));
        List<PageSkuResponseDTO> responseDTOList = page.getRecords().stream()
                .map(sku -> doConvertDto(sku, PageSkuResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(new LayuiPage<>(page.getTotal(), responseDTOList));
    }

    @Override
    protected void customerConvertDto(PageSkuResponseDTO result, SkuDO doo) {
        result.setCascadeCategoryName(categorySupport.getCascadeCategoryName(result.getCategoryId()));
        result.setBrandName(brandCacheService.selectById(result.getBrandId()).getName());
        result.setRecommendStatus(EnumUtil.getKeyValueEnum(YNEnum.class, doo.getRecommendStatus()));
        result.setNewStatus(EnumUtil.getKeyValueEnum(YNEnum.class, doo.getNewStatus()));
        result.setPreviewStatus(EnumUtil.getKeyValueEnum(YNEnum.class, doo.getPreviewStatus()));
        result.setAuditStatus(EnumUtil.getKeyValueEnum(SkuAuditStatusEnum.class, doo.getAuditStatus()));
    }
}
