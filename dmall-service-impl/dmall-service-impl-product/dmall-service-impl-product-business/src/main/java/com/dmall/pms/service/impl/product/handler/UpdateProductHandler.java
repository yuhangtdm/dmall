package com.dmall.pms.service.impl.product.handler;
import com.google.common.collect.Lists;
import com.dmall.pms.api.dto.product.request.attribute.AttributeTypeDTO;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.api.dto.product.request.save.ProductAttributeDTO;
import com.dmall.pms.api.dto.product.request.update.UpdateProductRequestDTO;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.service.impl.product.common.ProductValidate;
import com.dmall.pms.service.impl.product.enums.ProductErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Override
    public BaseResult<Long> validate(UpdateProductRequestDTO requestDTO) {
        // 校验id是否存在
        ProductDO productDO = productMapper.selectById(requestDTO.getId());
        if (productDO == null){
            return ResultUtil.fail(ProductErrorEnum.PRODUCT_NOT_EXIST);
        }
        // 校验商品名称必须唯一
        ProductDO nameProduct = productMapper.selectOne(Wrappers.<ProductDO>lambdaQuery().eq(ProductDO::getName, requestDTO.getName()));
        if (nameProduct != null && !nameProduct.getId().equals(requestDTO.getId())){
            return ResultUtil.fail(ProductErrorEnum.PRODUCT_NAME_EXISTS);
        }
        // 校验销售属性
        ProductAttributeDTO attributeDTO = new ProductAttributeDTO();
        attributeDTO.setCategoryId(productDO.getCategoryId());
        attributeDTO.setBrandId(productDO.getBrandId());
        attributeDTO.setSpecifications(requestDTO.getSpecifications());
        attributeDTO.setParams(requestDTO.getParams());
        return productValidate.validate(attributeDTO);
    }

    @Override
    public BaseResult<Long> processor(UpdateProductRequestDTO requestDTO) {
        ProductDO productDO = dtoConvertDo(requestDTO, ProductDO.class);
        productMapper.updateById(productDO);

        return ResultUtil.success();
    }

}
