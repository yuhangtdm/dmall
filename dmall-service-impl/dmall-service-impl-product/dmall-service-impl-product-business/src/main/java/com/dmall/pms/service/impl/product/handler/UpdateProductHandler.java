package com.dmall.pms.service.impl.product.handler;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.util.BeanUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.pms.api.dto.product.request.BasicProductRequestDTO;
import com.dmall.pms.api.dto.product.request.update.UpdateProductRequestDTO;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.dataobject.SkuAttributeValueDO;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.service.impl.validate.ProductValidate;
import com.dmall.pms.service.impl.support.ProductAttributeValueSupport;
import com.dmall.pms.service.impl.support.SkuAttributeValueSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 修改商品处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class UpdateProductHandler extends AbstractCommonHandler<UpdateProductRequestDTO, ProductDO, Long> {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductValidate productValidate;

    @Autowired
    private ProductAttributeValueSupport productAttributeValueSupport;

    @Autowired
    private SkuAttributeValueSupport skuAttributeValueSupport;

    @Override
    public BaseResult<Long> validate(UpdateProductRequestDTO requestDTO) {
        // 校验id是否存在
        ProductDO productDO = productMapper.selectById(requestDTO.getId());
        if (productDO == null) {
            return ResultUtil.fail(PmsErrorEnum.PRODUCT_NOT_EXISTS);
        }
        BasicProductRequestDTO basicProduct = requestDTO.getBasicProduct();
        // 校验商品名称必须唯一
        ProductDO nameProduct = productMapper.selectOne(Wrappers.<ProductDO>lambdaQuery().eq(ProductDO::getName, basicProduct.getName()));
        if (nameProduct != null && !nameProduct.getId().equals(requestDTO.getId())) {
            return ResultUtil.fail(PmsErrorEnum.PRODUCT_NAME_EXISTS);
        }
        // 该商品下已有sku属性值 不用校验商品属性
        List<SkuAttributeValueDO> skuAttributeValues = skuAttributeValueSupport.listByProductId(requestDTO.getId());
        if (CollUtil.isEmpty(skuAttributeValues)) {
            return ResultUtil.success();
        }
        return productValidate.attributeValidate(requestDTO.getExt());
    }

    @Override
    public BaseResult<Long> processor(UpdateProductRequestDTO requestDTO) {
        ProductDO productDO = BeanUtil.copyProperties(requestDTO.getBasicProduct(), ProductDO.class);
        productDO.setId(requestDTO.getId());
        // 更新商品
        productMapper.updateById(productDO);
        // 更新商品-属性 无sku才进行更新
        List<SkuAttributeValueDO> skuAttributeValueDOS = skuAttributeValueSupport.listByProductId(requestDTO.getId());
        if (CollUtil.isEmpty(skuAttributeValueDOS)) {
            productAttributeValueSupport.saveProductAttributeValue(productDO, requestDTO.getExt());
        }
        return ResultUtil.success(requestDTO.getId());
    }

}
