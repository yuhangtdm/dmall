package com.dmall.pms.service.impl.product.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.handler.BeanUtil;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.category.enums.LevelEnum;
import com.dmall.pms.api.dto.product.request.save.*;
import com.dmall.pms.generator.dataobject.*;
import com.dmall.pms.generator.mapper.AttributeMapper;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import com.dmall.pms.generator.mapper.CategoryBrandMapper;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.generator.service.IProductAttributeService;
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
 * @description: 新增商品处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class SaveProductHandler extends AbstractCommonHandler<SaveProductRequestDTO, ProductDO, Long> {

    @Autowired
    private ProductMapper productMapper;

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

    @Autowired
    private IProductAttributeService iProductAttributeService;


    @Override
    public BaseResult<Long> validate(SaveProductRequestDTO requestDTO) {
        ProductAttributeDTO attributeDTO = requestDTO.getAttribute();
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

    @Override
    public BaseResult<Long> processor(SaveProductRequestDTO requestDTO) {
        ProductDO productDO = buildProductDO(requestDTO);
        productMapper.insert(productDO);
        // 设置 pms_attribute
        List<ProductAttributeDO> productAttributeDOS = buildProductAttributeDOs(productDO.getId(), requestDTO.getAttribute());
        iProductAttributeService.saveBatch(productAttributeDOS);

        //todo 保存es
        return ResultUtil.success(productDO.getId());
    }

    /**
     * 构建商品对象
     */
    private ProductDO buildProductDO(SaveProductRequestDTO requestDTO) {
        BasicProductDTO basicProduct = requestDTO.getBasicProduct();
        ProductAttributeDTO productAttribute = requestDTO.getAttribute();
        ProductDO productDO = BeanUtil.copyProperties(basicProduct, ProductDO.class);
        productDO.setCategoryId(productAttribute.getCategoryId());
        CategoryDO categoryDO = categoryCacheService.selectById(productAttribute.getCategoryId());
        productDO.setCascadeCategoryId(categoryDO.getPath());
        productDO.setBrandId(productAttribute.getBrandId());
        // todo 构建商品编号
        return productDO;
    }

    /**
     * 构建销售属性
     */
    private List<ProductAttributeDO> buildProductAttributeDOs(Long productId, ProductAttributeDTO attribute) {
        List<ProductAttributeDO> productAttributeDOS = Lists.newArrayList();
        AttributeTypeDTO specifications = attribute.getSpecifications();

        // 构建销售规格
        buildAttributeDOs(productAttributeDOS, productId, specifications.getAttributeTypeId(), specifications.getAttributes());

        // 构建销售参数
        List<AttributeTypeDTO> params = attribute.getParams();
        for (AttributeTypeDTO param : params) {
            buildAttributeDOs(productAttributeDOS, productId, param.getAttributeTypeId(), param.getAttributes());
        }

        return productAttributeDOS;
    }

    /**
     * 构建商品-属性值
     */
    private void buildAttributeDOs(List<ProductAttributeDO> productAttributeDOS, Long productId, Long attributeTypeId, List<AttributeDTO> attributeDTOS) {
        for (AttributeDTO specificationsAttribute : attributeDTOS) {
            for (AttributeValueDTO attributeValue : specificationsAttribute.getAttributeValues()) {
                ProductAttributeDO productAttributeDO = new ProductAttributeDO();
                productAttributeDO.setProductId(productId);
                productAttributeDO.setAttributeTypeId(attributeTypeId);
                productAttributeDO.setAttributeId(specificationsAttribute.getAttributeId());
                productAttributeDO.setAttributeValue(attributeValue.getValue());
                productAttributeDO.setPic(attributeValue.getPic());
                productAttributeDO.setAttributeType(specificationsAttribute.getAttributeType());
                productAttributeDOS.add(productAttributeDO);
            }
        }
    }

}
