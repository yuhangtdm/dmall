package com.dmall.pms.service.impl.product.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.BeanUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.product.request.BasicProductRequestDTO;
import com.dmall.pms.api.dto.product.request.attributevalue.ProductExtRequestDTO;
import com.dmall.pms.api.dto.product.request.save.SaveProductRequestDTO;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.dataobject.CategoryProductDO;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.mapper.CategoryProductMapper;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.support.ProductAttributeValueSupport;
import com.dmall.pms.service.support.ProductSupport;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增商品处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class SaveProductHandler extends AbstractCommonHandler<SaveProductRequestDTO, ProductDO, Long> {

    @Autowired
    private PmsValidate pmsValidate;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryProductMapper categoryProductMapper;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private ProductSupport productSupport;

    @Autowired
    private ProductAttributeValueSupport productAttributeValueSupport;

    @Override
    public BaseResult<Long> validate(SaveProductRequestDTO requestDTO) {
        // 商品名称必须唯一
        ProductDO productDO = productSupport.getByName(requestDTO.getBasicProduct().getName());
        if (productDO != null) {
            return ResultUtil.fail(PmsErrorEnum.PRODUCT_NAME_EXISTS);
        }
        // 校验商品扩展参数
        return pmsValidate.productExtValidate(requestDTO.getExt());
    }

    @Override
    public BaseResult<Long> processor(SaveProductRequestDTO requestDTO) {
        // 保存商品
        ProductDO productDO = saveProductDO(requestDTO);
        // 保存商品属性值
        productAttributeValueSupport.saveProductAttributeValue(productDO, requestDTO.getExt().getProductAttribute());
        return ResultUtil.success(productDO.getId());
    }

    /**
     * 构建商品对象
     */
    private ProductDO saveProductDO(SaveProductRequestDTO requestDTO) {
        BasicProductRequestDTO basicProduct = requestDTO.getBasicProduct();
        ProductExtRequestDTO productAttribute = requestDTO.getExt();
        ProductDO productDO = BeanUtil.copyProperties(basicProduct, ProductDO.class);
        productDO.setBrandId(productAttribute.getBrandId());
        productMapper.insert(productDO);
        // 分类-商品
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
