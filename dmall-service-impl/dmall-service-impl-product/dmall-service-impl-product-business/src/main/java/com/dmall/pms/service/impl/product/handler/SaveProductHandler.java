package com.dmall.pms.service.impl.product.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.handler.BeanUtil;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.util.NoUtil;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.product.common.BasicProductRequestDTO;
import com.dmall.pms.api.dto.product.request.attributevalue.ProductExtRequestDTO;
import com.dmall.pms.api.dto.product.request.save.SaveProductRequestDTO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.dataobject.CategoryProductDO;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.mapper.CategoryProductMapper;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.impl.product.common.ProductValidate;
import com.dmall.pms.service.impl.product.enums.ProductErrorEnum;
import com.dmall.pms.service.impl.support.ProductAttributeValueSupport;
import com.dmall.pms.service.impl.support.SkuSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private SkuSupport skuSupport;

    @Autowired
    private CategoryProductMapper categoryProductMapper;

    @Autowired
    private ProductValidate productValidate;

    @Autowired
    private ProductAttributeValueSupport productAttributeValueSupport;

    @Override
    public BaseResult<Long> validate(SaveProductRequestDTO requestDTO) {
        // 商品名称必须唯一
        ProductDO productDO = productMapper.selectOne(Wrappers.<ProductDO>lambdaQuery()
                .eq(ProductDO::getName, requestDTO.getBasicProduct().getName()));
        if (productDO != null) {
            return ResultUtil.fail(ProductErrorEnum.PRODUCT_NAME_EXISTS);
        }
        // 校验商品扩展参数
        return productValidate.validate(requestDTO.getExt());
    }

    @Override
    public BaseResult<Long> processor(SaveProductRequestDTO requestDTO) {
        // 保存商品
        ProductDO productDO = saveProductDO(requestDTO);
        // 保存商品属性值
        ProductExtRequestDTO ext = requestDTO.getExt();
        productAttributeValueSupport.saveProductAttributeValue(productDO, ext);
        // 保存sku
        skuSupport.saveSku(productDO.getId(), ext.getCategoryIds(), ext.getBrandId(), requestDTO.getSkuList());
        return ResultUtil.success(productDO.getId());
    }

    /**
     * 构建商品对象
     */
    private ProductDO saveProductDO(SaveProductRequestDTO requestDTO) {
        BasicProductRequestDTO basicProduct = requestDTO.getBasicProduct();
        ProductExtRequestDTO productAttribute = requestDTO.getExt();
        ProductDO productDO = BeanUtil.copyProperties(basicProduct, ProductDO.class);
        productDO.setProductNo(NoUtil.generateProductNo());
        productDO.setBrandId(productAttribute.getBrandId());
        productMapper.insert(productDO);
        for (Long categoryId : requestDTO.getExt().getCategoryIds()) {
            CategoryProductDO categoryProductDO = new CategoryProductDO();
            categoryProductDO.setCategoryId(categoryId);
            categoryProductDO.setProductId(productDO.getId());
            CategoryDO categoryDO = categoryCacheService.selectById(categoryId);
            categoryProductDO.setCascadeCategoryId(categoryDO.getPath());
            categoryProductMapper.insert(categoryProductDO);
        }

        return productDO;
    }

}
