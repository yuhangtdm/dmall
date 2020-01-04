package com.dmall.pms.service.impl.product.handler;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.handler.BeanUtil;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.product.common.BasicProductRequestDTO;
import com.dmall.pms.api.dto.product.request.update.UpdateProductRequestDTO;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.dataobject.SkuAttributeValueDO;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.generator.mapper.SkuAttributeValueMapper;
import com.dmall.pms.service.impl.product.common.ProductValidate;
import com.dmall.pms.service.impl.product.enums.ProductErrorEnum;
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
    private SkuAttributeValueMapper skuAttributeValueMapper;

    @Autowired
    private SkuAttributeValueSupport skuAttributeValueSupport;

    @Override
    public BaseResult<Long> validate(UpdateProductRequestDTO requestDTO) {
        // 校验id是否存在
        ProductDO productDO = productMapper.selectById(requestDTO.getId());
        if (productDO == null) {
            return ResultUtil.fail(ProductErrorEnum.PRODUCT_NOT_EXIST);
        }
        BasicProductRequestDTO basicProduct = requestDTO.getBasicProduct();
        // 校验商品名称必须唯一
        ProductDO nameProduct = productMapper.selectOne(Wrappers.<ProductDO>lambdaQuery().eq(ProductDO::getName, basicProduct.getName()));
        if (nameProduct != null && !nameProduct.getId().equals(requestDTO.getId())) {
            return ResultUtil.fail(ProductErrorEnum.PRODUCT_NAME_EXISTS);
        }
        //
        List<SkuAttributeValueDO> skuAttributeValueDOS = skuAttributeValueSupport.listByProductId(requestDTO.getId());
        if (CollUtil.isEmpty(skuAttributeValueDOS)) {
            return ResultUtil.success();
        }
        return productValidate.validate(requestDTO.getExt());
    }

    @Override
    public BaseResult<Long> processor(UpdateProductRequestDTO requestDTO) {
        ProductDO productDO = BeanUtil.copyProperties(requestDTO.getBasicProduct(), ProductDO.class);
        productDO.setId(requestDTO.getId());
        // 更新商品
        productMapper.updateById(productDO);
        // 更新商品-属性 无sku才进行更新
        List<SkuAttributeValueDO> skuAttributeValueDOS = skuAttributeValueMapper.selectList(Wrappers.<SkuAttributeValueDO>lambdaQuery()
                .eq(SkuAttributeValueDO::getProductId, requestDTO.getId()));
        if (CollUtil.isEmpty(skuAttributeValueDOS)) {
            productAttributeValueSupport.saveProductAttributeValue(productDO, requestDTO.getExt());
        }
        return ResultUtil.success(requestDTO.getId());
    }


}
