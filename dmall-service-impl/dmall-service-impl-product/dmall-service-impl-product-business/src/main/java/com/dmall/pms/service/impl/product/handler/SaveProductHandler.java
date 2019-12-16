package com.dmall.pms.service.impl.product.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.handler.BeanUtil;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.util.NoUtil;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.product.request.attribute.AttributeTypeDTO;
import com.dmall.pms.api.dto.product.request.save.BasicProductRequestDTO;
import com.dmall.pms.api.dto.product.request.save.ProductAttributeDTO;
import com.dmall.pms.api.dto.product.request.save.SaveProductRequestDTO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.dataobject.ProductAttributeDO;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.generator.service.IProductAttributeService;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.impl.product.attribute.ProductAttributeBuilder;
import com.dmall.pms.service.impl.product.common.ProductValidate;
import com.dmall.pms.service.impl.product.enums.ProductErrorEnum;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    private IProductAttributeService iProductAttributeService;

    @Autowired
    private ProductValidate productValidate;

    @Override
    public BaseResult<Long> validate(SaveProductRequestDTO requestDTO) {
        // 商品名称必须唯一
        ProductDO productDO = productMapper.selectOne(Wrappers.<ProductDO>lambdaQuery()
                .eq(ProductDO::getName, requestDTO.getBasicProduct().getName()));
        if (productDO != null) {
            return ResultUtil.fail(ProductErrorEnum.PRODUCT_NAME_EXISTS);
        }
        // 校验销售属性
        return productValidate.validate(requestDTO.getAttribute());
    }

    @Override
    public BaseResult<Long> processor(SaveProductRequestDTO requestDTO) {
        ProductDO productDO = buildProductDO(requestDTO);
        productMapper.insert(productDO);
        // 保存pms_attribute
        List<ProductAttributeDO> productAttributeDOS = buildProductAttributeDOs(productDO.getId(), requestDTO.getAttribute());
        iProductAttributeService.saveBatch(productAttributeDOS);
        return ResultUtil.success(productDO.getId());
    }

    /**
     * 构建商品对象
     */
    private ProductDO buildProductDO(SaveProductRequestDTO requestDTO) {
        BasicProductRequestDTO basicProduct = requestDTO.getBasicProduct();
        ProductAttributeDTO productAttribute = requestDTO.getAttribute();
        ProductDO productDO = BeanUtil.copyProperties(basicProduct, ProductDO.class);
        productDO.setProductNo(NoUtil.generateProductNo());
        productDO.setCategoryId(productAttribute.getCategoryId());
        CategoryDO categoryDO = categoryCacheService.selectById(productAttribute.getCategoryId());
        productDO.setCascadeCategoryId(categoryDO.getPath());
        productDO.setBrandId(productAttribute.getBrandId());
        return productDO;
    }

    /**
     * 构建销售属性
     */
    private List<ProductAttributeDO> buildProductAttributeDOs(Long productId, ProductAttributeDTO attribute) {
        List<ProductAttributeDO> productAttributeDOS = Lists.newArrayList();
        AttributeTypeDTO specifications = attribute.getSpecifications();
        // 构建销售规格
        ProductAttributeBuilder.buildAttributeDOs(productAttributeDOS, productId, specifications.getAttributeTypeId(), specifications.getAttributes());
        // 构建销售参数
        List<AttributeTypeDTO> params = attribute.getParams();
        for (AttributeTypeDTO param : params) {
            ProductAttributeBuilder.buildAttributeDOs(productAttributeDOS, productId, param.getAttributeTypeId(), param.getAttributes());
        }
        return productAttributeDOS;
    }


}
