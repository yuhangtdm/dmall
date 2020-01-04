package com.dmall.pms.service.impl.product.common;

import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.category.enums.LevelEnum;
import com.dmall.pms.api.dto.product.request.attributevalue.*;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.impl.brand.cache.BrandCacheService;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.impl.product.enums.ProductErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: 商品公共校验逻辑
 * @author: created by hang.yu on 2019/12/15 21:57
 */
@Component
public class ProductValidate {

    @Autowired
    private BrandCacheService brandCacheService;

    @Autowired
    private AttributeCacheService attributeCacheService;

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private CategoryCacheService categoryCacheService;

    public BaseResult validate(ProductExtRequestDTO extDTO) {
        List<Long> categoryIds = extDTO.getCategoryIds();
        Long[] categoryIdArray = new Long[categoryIds.size()];
        Set<Long> set = new HashSet<>(categoryIds);
        // 商品分类不能重复
        if (set.size() != categoryIds.size()) {
            return ResultUtil.fail(ProductErrorEnum.CATEGORY_NOT_REPEATED);
        }
        // 分类存在 且是三级分类,品牌存在
        BaseResult validate = basicValidate(extDTO.getBrandId(), categoryIds.toArray(categoryIdArray));
        if (!validate.getResult()) {
            return validate;
        }

        return attributeValidate(extDTO.getProductAttribute());
    }

    /**
     * 分类及品牌校验
     */
    public BaseResult basicValidate(Long brandId, Long... categoryIds) {
        for (Long categoryId : categoryIds) {
            CategoryDO categoryDO = categoryCacheService.selectById(categoryId);
            if (categoryDO == null) {
                return ResultUtil.fail(ProductErrorEnum.CATEGORY_NOT_EXISTS);
            }
            // 校验是否是三级分类
            if (!LevelEnum.THREE.getCode().equals(categoryDO.getLevel())) {
                return ResultUtil.fail(ProductErrorEnum.CATEGORY_LEVEL_ERROR);
            }
        }

        // 校验品牌是否存在
        BrandDO brandDO = brandCacheService.selectById(brandId);
        if (brandDO == null) {
            return ResultUtil.fail(ProductErrorEnum.BRAND_NOT_EXISTS);
        }
        return ResultUtil.success();
    }

    /**
     * 商品属性值校验
     */
    public BaseResult attributeValidate(ProductAttributeRequestDTO attributeRequest){
        // 属性存在
        for (SpecificationsRequestDTO specification : attributeRequest.getSpecifications()) {
            AttributeDO attributeDO = attributeCacheService.selectById(specification.getAttributeId());
            if (attributeDO == null) {
                return ResultUtil.fail(ProductErrorEnum.ATTRIBUTE_NOT_EXISTS);
            }
        }
        // 属性存在
        for (SalePointRequestDTO salePoint : attributeRequest.getSalePoints()) {
            AttributeDO attributeDO = attributeCacheService.selectById(salePoint.getAttributeId());
            if (attributeDO == null) {
                return ResultUtil.fail(ProductErrorEnum.ATTRIBUTE_NOT_EXISTS);
            }
        }
        // 属性分类存在
        for (ParamRequestDTO param : attributeRequest.getParams()) {
            AttributeTypeDO attributeTypeDO = attributeTypeCacheService.selectById(param.getAttributeTypeId());
            if (attributeTypeDO == null) {
                return ResultUtil.fail(ProductErrorEnum.ATTRIBUTE_TYPE_NOT_EXISTS);
            }
            for (ParamValueRequestDTO paramAttribute : param.getParamAttributes()) {
                AttributeDO attributeDO = attributeCacheService.selectById(paramAttribute.getAttributeId());
                if (attributeDO == null) {
                    return ResultUtil.fail(ProductErrorEnum.ATTRIBUTE_NOT_EXISTS);
                }
            }
        }
        return ResultUtil.success();
    }
}
