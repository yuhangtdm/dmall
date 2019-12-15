package com.dmall.pms.service.impl.product.common;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.category.enums.LevelEnum;
import com.dmall.pms.api.dto.product.request.attribute.AttributeDTO;
import com.dmall.pms.api.dto.product.request.attribute.AttributeTypeDTO;
import com.dmall.pms.api.dto.product.request.save.ProductAttributeDTO;
import com.dmall.pms.generator.dataobject.*;
import com.dmall.pms.generator.mapper.AttributeMapper;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import com.dmall.pms.generator.mapper.CategoryBrandMapper;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.impl.brand.cache.BrandCacheService;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.impl.product.enums.ProductErrorEnum;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 商品公共校验逻辑
 * @author: created by hang.yu on 2019/12/15 21:57
 */
@Component
public class ProductValidate {

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private BrandCacheService brandCacheService;

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private AttributeMapper attributeMapper;

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    public BaseResult validate(ProductAttributeDTO attributeDTO) {
        // 校验商品分类是否存在
        CategoryDO categoryDO = categoryCacheService.selectById(attributeDTO.getCategoryId());
        if (categoryDO == null) {
            return ResultUtil.fail(ProductErrorEnum.CATEGORY_NOT_EXISTS);
        }
        // 校验是否是三级分类
        if (!LevelEnum.THREE.getCode().equals(categoryDO.getLevel())) {
            return ResultUtil.fail(ProductErrorEnum.CATEGORY_LEVEL_ERROR);
        }
        // 校验品牌是否存在
        BrandDO brandDO = brandCacheService.selectById(attributeDTO.getBrandId());
        if (brandDO == null) {
            return ResultUtil.fail(ProductErrorEnum.BRAND_NOT_EXISTS);
        }
        // 校验品牌是否是该商品分类下的
        CategoryBrandDO categoryBrandDO = categoryBrandMapper.selectOne(Wrappers.<CategoryBrandDO>lambdaQuery()
                .eq(CategoryBrandDO::getCategoryId, categoryDO.getId())
                .eq(CategoryBrandDO::getBrandId, brandDO.getId()));
        if (categoryBrandDO == null) {
            return ResultUtil.fail(ProductErrorEnum.CATEGORY_BRAND_ERROR);
        }
        /*
            校验销售规格
         */
        // 校验商品属性分类是否存在
        AttributeTypeDTO specifications = attributeDTO.getSpecifications();
        AttributeTypeDO specificationsAttributeTypeDO = attributeTypeCacheService.selectById(specifications.getAttributeTypeId());
        if (specificationsAttributeTypeDO == null) {
            return ResultUtil.fail(ProductErrorEnum.SPECIFICATIONS_ATTRIBUTE_TYPE_NOT_EXISTS);
        }
        // 校验商品属性分类是否是商品分类下的
        if (!categoryDO.getId().equals(specificationsAttributeTypeDO.getCategoryId())) {
            return ResultUtil.fail(ProductErrorEnum.CATEGORY_ATTRIBUTE_TYPE_ERROR);
        }
        List<Long> specificationsAttributeIds = specifications.getAttributes().stream()
                .map(AttributeDTO::getAttributeId).collect(Collectors.toList());
        // 校验商品属性是否存在
        List<AttributeDO> attributeDOS = attributeMapper.selectBatchIds(specificationsAttributeIds);
        if (specificationsAttributeIds.size() != attributeDOS.size()) {
            return ResultUtil.fail(ProductErrorEnum.ATTRIBUTE_NOT_EXISTS);
        }
        // 校验商品属性是否是商品属性分类下的
        boolean s = attributeDOS.stream().anyMatch(attributeDO ->
                !attributeDO.getAttributeTypeId().equals(specificationsAttributeTypeDO.getId()));
        if (s) {
            return ResultUtil.fail(ProductErrorEnum.ATTRIBUTE_TYPE_ATTRIBUTE_ERROR);
        }
        /*
            校验销售参数
         */
        List<AttributeTypeDTO> params = attributeDTO.getParams();
        List<Long> paramsAttributeTypeIds = params.stream().map(AttributeTypeDTO::getAttributeTypeId).collect(Collectors.toList());
        List<AttributeTypeDO> attributeTypeDOS = attributeTypeMapper.selectBatchIds(paramsAttributeTypeIds);

        // 校验商品属性分类是否存在
        if (attributeTypeDOS.size() != params.size()) {
            return ResultUtil.fail(ProductErrorEnum.PARAMS_ATTRIBUTE_TYPE_NOT_EXISTS);
        }
        // 校验商品属性分类是否是商品分类下的
        boolean c = attributeTypeDOS.stream().anyMatch(attributeTypeDO ->
                !categoryDO.getId().equals(attributeTypeDO.getCategoryId()));
        if (c) {
            return ResultUtil.fail(ProductErrorEnum.PARAMS_CATEGORY_ATTRIBUTE_TYPE_ERROR);
        }
        // 校验商品属性是否存在
        List<AttributeDTO> allParamsAttributeDTO = Lists.newArrayList();
        for (AttributeTypeDTO param : params) {
            allParamsAttributeDTO.addAll(param.getAttributes());
        }
        List<Long> AllParamsAttributeIds = allParamsAttributeDTO.stream().map(AttributeDTO::getAttributeId).collect(Collectors.toList());
        List<AttributeDO> paramsAttributeDOs = attributeMapper.selectBatchIds(specificationsAttributeIds);

        // 校验商品属性是否存在
        if (AllParamsAttributeIds.size() != paramsAttributeDOs.size()) {
            return ResultUtil.fail(ProductErrorEnum.PARAMS_ATTRIBUTE_NOT_EXISTS);
        }
        // 校验商品属性是否是商品属性分类下的 不进行校验

        return ResultUtil.success();
    }
}
