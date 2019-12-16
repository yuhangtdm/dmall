package com.dmall.pms.service.impl.product.attribute;

import com.dmall.pms.api.dto.product.request.attribute.AttributeDTO;
import com.dmall.pms.api.dto.product.request.attribute.AttributeValueDTO;
import com.dmall.pms.generator.dataobject.ProductAttributeDO;

import java.util.List;

/**
 * @description: ProductAttributeBuilder
 * @author: created by hang.yu on 2019/12/16 11:00
 */
public class ProductAttributeBuilder {

    /**
     * 构建商品-属性值
     *
     * @param productAttributeDOS 返回结果
     * @param productId           商品id
     * @param attributeTypeId     属性分类id
     * @param attributeDTOS       属性列表
     */
    public static void buildAttributeDOs(List<ProductAttributeDO> productAttributeDOS, Long productId,
                                         Long attributeTypeId, List<AttributeDTO> attributeDTOS) {
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
