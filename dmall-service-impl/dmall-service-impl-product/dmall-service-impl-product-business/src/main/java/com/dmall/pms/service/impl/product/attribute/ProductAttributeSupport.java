package com.dmall.pms.service.impl.product.attribute;

import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/16 16:26
 */
@Component
public class ProductAttributeSupport {



    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private AttributeCacheService attributeCacheService;

    /**
     * 设置销售规格和参数
     */
//    public GetProductAttributeResponseDTO setSaleAttribute(Long id) {
//        GetProductAttributeResponseDTO responseDTO = new GetProductAttributeResponseDTO();
//        // 获取该商品的所有参数
//        List<ProductAttributeDO> productAttributeList = productAttributeMapper.selectList(Wrappers.<ProductAttributeDO>lambdaQuery()
//                .eq(ProductAttributeDO::getProductId, id));
//        if (CollUtil.isNotEmpty(productAttributeList)) {
//            // 按照属性分类id分组
//            Map<Long, List<ProductAttributeDO>> attributeTypeMap = productAttributeList.stream()
//                    .collect(Collectors.groupingBy(ProductAttributeDO::getAttributeTypeId, Collectors.toList()));
//            List<ProductAttributeTypeDTO> params = Lists.newArrayList();
//            attributeTypeMap.forEach((attributeTypeId, attributeList) -> {
//                ProductAttributeTypeDTO productAttributeType = new ProductAttributeTypeDTO();
//                productAttributeType.setAttributeTypeId(attributeTypeId);
//                AttributeTypeDO attributeTypeDO = attributeTypeCacheService.selectById(attributeTypeId);
//                productAttributeType.setAttributeShowName(attributeTypeDO.getShowName());
////                productAttributeType.setType(EnumUtil.getKeyValueEnum(AttributeTypeEnum.class, attributeTypeDO.getType()));
//                // 按照属性id分组
//                Map<Long, List<ProductAttributeDO>> attributeMap = attributeList.stream()
//                        .collect(Collectors.groupingBy(ProductAttributeDO::getAttributeId, Collectors.toList()));
//                attributeMap.forEach((attributeId, attributeValueList) -> {
//                    ProductAttributeDTO attributeDTO = new ProductAttributeDTO();
//                    attributeDTO.setAttributeId(attributeId);
//                    attributeDTO.setAttributeName(attributeCacheService.selectById(attributeId).getName());
//                    List<ProductAttributeValueDTO> attributeValues = attributeValueList.stream().map(attributeValueDO -> {
//                        ProductAttributeValueDTO attributeValue = new ProductAttributeValueDTO();
//                        attributeValue.setProductAttributeValueId(attributeValueDO.getId());
//                        attributeValue.setAttributeValue(attributeValueDO.getAttributeValue());
//                        attributeValue.setAttributePic(attributeValueDO.getPic());
//                        return attributeValue;
//                    }).collect(Collectors.toList());
//                    attributeDTO.setAttributeValues(attributeValues);
//                });
//                // 设置销售规格或参数
//                /*if (AttributeTypeEnum.SPECIFICATIONS.getCode().equals(attributeTypeDO.getType())) {
//                    responseDTO.setSpecifications(productAttributeType);
//                } else {
//                    params.add(productAttributeType);
//                }*/
//            });
//            responseDTO.setParams(params);
//        }
//        return responseDTO;
//    }
}
