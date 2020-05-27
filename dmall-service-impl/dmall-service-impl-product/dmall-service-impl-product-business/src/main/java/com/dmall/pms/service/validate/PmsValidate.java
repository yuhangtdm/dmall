package com.dmall.pms.service.validate;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.common.util.ResultUtil;
import com.dmall.pms.api.dto.product.request.attributevalue.*;
import com.dmall.pms.api.enums.InputTypeEnum;
import com.dmall.pms.api.enums.LevelEnum;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.*;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.generator.mapper.SkuAuditMapper;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.impl.brand.cache.BrandCacheService;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: Pms公共校验服务
 * @author: created by hang.yu on 2020/4/25 10:08
 */
@Component
public class PmsValidate {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private AttributeCacheService attributeCacheService;

    @Autowired
    private BrandCacheService brandCacheService;

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private SkuAuditMapper skuAuditMapper;

    /**
     * 校验商品必须存在
     */
    public ProductDO validateProduct(Long productId) {
        // 校验商品是否已删除
        ProductDO productDO = productMapper.selectById(productId);
        if (productDO == null) {
            throw new BusinessException(PmsErrorEnum.PRODUCT_NOT_EXISTS);
        }
        return productDO;
    }

    /**
     * 校验sku必须存在
     */
    public SkuDO validateSku(Long skuId) {
        SkuDO sku = skuMapper.selectById(skuId);
        if (sku == null) {
            throw new BusinessException(PmsErrorEnum.SKU_NOT_EXISTS);
        }
        return sku;
    }

    /**
     * 校验商品分类必须存在
     */
    public CategoryDO validateCategory(Long categoryId) {
        CategoryDO categoryDO = categoryCacheService.selectById(categoryId);
        if (categoryDO == null) {
            throw new BusinessException(PmsErrorEnum.CATEGORY_NOT_EXIST);
        }
        return categoryDO;
    }

    /**
     * 校验分类
     */
    public BaseResult validateThreeLevelCategory(Long categoryId) {
        CategoryDO categoryDO = validateCategory(categoryId);
        // 分类级别必须是3级
        if (!LevelEnum.THREE.getCode().equals(categoryDO.getLevel())) {
            return ResultUtil.fail(PmsErrorEnum.PARENT_LEVEL_ERROR);
        }
        return ResultUtil.success();
    }

    /**
     * 校验品牌必须存在
     */
    public BrandDO validateBrand(Long brandId) {
        // 品牌必须存在
        BrandDO brandDO = brandCacheService.selectById(brandId);
        if (brandDO == null) {
            throw new BusinessException(PmsErrorEnum.BRAND_NOT_EXIST);
        }
        return brandDO;
    }

    /**
     * 校验商品属性类别必须存在
     */
    public AttributeTypeDO validateAttributeType(Long attributeTypeId) {
        AttributeTypeDO attributeTypeDO = attributeTypeCacheService.selectById(attributeTypeId);
        if (attributeTypeDO == null) {
            throw new BusinessException(PmsErrorEnum.ATTRIBUTE_TYPE_NOT_EXIST);
        }
        return attributeTypeDO;
    }

    /**
     * 校验商品属性必须存在
     */
    public AttributeDO validateAttribute(Long attributeId) {
        AttributeDO attributeDO = attributeCacheService.selectById(attributeId);
        if (attributeDO == null) {
            throw new BusinessException(PmsErrorEnum.ATTRIBUTE_NOT_EXIST);
        }
        return attributeDO;
    }

    /**
     * 校验审核记录必须存在
     */
    public SkuAuditDO validateAudit(Long auditId) {
        SkuAuditDO skuAuditDO = skuAuditMapper.selectById(auditId);
        if (skuAuditDO == null) {
            throw new BusinessException(PmsErrorEnum.AUDIT_NOT_EXIST);
        }
        return skuAuditDO;
    }

    /**
     * 新增或更新的公共校验
     */
    public BaseResult attributeValidate(Integer inputType, String inputList, String handAddStatus) {
        // 从列表获取 不支持新增 可选值为空
        if (InputTypeEnum.LIST.getCode().equals(inputType)) {
            if (StrUtil.isBlank(handAddStatus)) {
                return ResultUtil.fail(PmsErrorEnum.HAND_ADD_STATUS_EMPTY);
            }
            if (StrUtil.isBlank(inputList)) {
                return ResultUtil.fail(PmsErrorEnum.INPUT_LIST_BLANK);
            }
            String[] split = inputList.split(StrUtil.COMMA);
            HashSet<String> strings = Sets.newHashSet(inputList.split(StrUtil.COMMA));
            if (strings.size() != split.length) {
                return ResultUtil.fail(PmsErrorEnum.INPUT_LIST_INVALID);
            }
        }
        return ResultUtil.success();
    }

    /**
     * 校验商品扩展信息
     */
    public BaseResult productExtValidate(ProductExtRequestDTO extDTO) {
        List<Long> categoryIds = extDTO.getCategoryIds();
        Long[] categoryIdArray = new Long[categoryIds.size()];
        Set<Long> set = new HashSet<>(categoryIds);
        // 商品分类不能重复
        if (set.size() != categoryIds.size()) {
            return ResultUtil.fail(PmsErrorEnum.CATEGORY_NOT_REPEATED);
        }
        // 分类存在 且是三级分类,品牌存在
        BaseResult validate = commonValidate(extDTO.getBrandId(), categoryIds.toArray(categoryIdArray));
        if (!validate.getResult()) {
            return validate;
        }

        return attributeValidate(extDTO.getProductAttribute());
    }

    /**
     * 分类及品牌校验
     */
    public BaseResult commonValidate(Long brandId, Long... categoryIds) {
        for (Long categoryId : categoryIds) {
            CategoryDO categoryDO = categoryCacheService.selectById(categoryId);
            if (categoryDO == null) {
                return ResultUtil.fail(PmsErrorEnum.CATEGORY_NOT_EXIST);
            }
            // 校验是否是三级分类
            if (!LevelEnum.THREE.getCode().equals(categoryDO.getLevel())) {
                return ResultUtil.fail(PmsErrorEnum.CATEGORY_LEVEL_ERROR);
            }
        }

        // 校验品牌是否存在
        BrandDO brandDO = brandCacheService.selectById(brandId);
        if (brandDO == null) {
            return ResultUtil.fail(PmsErrorEnum.BRAND_NOT_EXIST);
        }
        return ResultUtil.success();
    }

    /**
     * 商品属性值校验
     */
    public BaseResult attributeValidate(ProductAttributeRequestDTO attributeRequest) {
        // 属性存在
        for (SpecificationsRequestDTO specification : attributeRequest.getSpecifications()) {
            AttributeDO attributeDO = attributeCacheService.selectById(specification.getAttributeId());
            if (attributeDO == null) {
                return ResultUtil.fail(PmsErrorEnum.ATTRIBUTE_NOT_EXIST);
            }
        }
        // 属性存在
        for (SalePointRequestDTO salePoint : attributeRequest.getSalePoints()) {
            AttributeDO attributeDO = attributeCacheService.selectById(salePoint.getAttributeId());
            if (attributeDO == null) {
                return ResultUtil.fail(PmsErrorEnum.ATTRIBUTE_NOT_EXIST);
            }
        }
        // 属性类别存在
        for (ParamRequestDTO param : attributeRequest.getParams()) {
            AttributeTypeDO attributeTypeDO = attributeTypeCacheService.selectById(param.getAttributeTypeId());
            if (attributeTypeDO == null) {
                return ResultUtil.fail(PmsErrorEnum.ATTRIBUTE_TYPE_NOT_EXIST);
            }
            for (ParamValueRequestDTO paramAttribute : param.getParamAttributes()) {
                AttributeDO attributeDO = attributeCacheService.selectById(paramAttribute.getAttributeId());
                if (attributeDO == null) {
                    return ResultUtil.fail(PmsErrorEnum.ATTRIBUTE_TYPE_NOT_EXIST);
                }
            }
        }
        return ResultUtil.success();
    }

    /**
     * sku公共校验逻辑
     */
    public BaseResult skuValidate(Long productId, Long skuId) {
        // 商品必须存在
        validateProduct(productId);
        validateSku(skuId);
        return ResultUtil.success();
    }

}
