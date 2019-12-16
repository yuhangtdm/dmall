package com.dmall.pms.service.impl.product.handler;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.enums.base.EnumUtil;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.handler.BeanUtil;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.attributetype.enums.AttributeTypeEnum;
import com.dmall.pms.api.dto.product.response.get.*;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.dataobject.ProductAttributeDO;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.mapper.ProductAttributeMapper;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.impl.product.enums.ProductErrorEnum;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 查询商品处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class GetProductHandler extends AbstractCommonHandler<Long, ProductDO, GetProductResponseDTO> {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductAttributeMapper productAttributeMapper;

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private AttributeCacheService attributeCacheService;

    @Override
    public BaseResult<GetProductResponseDTO> processor(Long id) {
        ProductDO productDO = productMapper.selectById(id);
        if (productDO == null){
            return ResultUtil.fail(ProductErrorEnum.PRODUCT_NOT_EXIST);
        }
        GetProductResponseDTO responseDTO = new GetProductResponseDTO();
        // 设置商品基本信息
        BasicProductResponseDTO basicProduct = BeanUtil.copyProperties(productDO, BasicProductResponseDTO.class);
        responseDTO.setBasicProduct(basicProduct);
        // 设置商品销售规格和参数
        setSaleAttribute(responseDTO, id);
        //todo 设置sku
        return ResultUtil.success(responseDTO);
    }

    /**
     * 设置销售规格和参数
     */
    private void setSaleAttribute(GetProductResponseDTO responseDTO, Long id) {
        // 获取该商品的所有参数
        List<ProductAttributeDO> productAttributeList = productAttributeMapper.selectList(Wrappers.<ProductAttributeDO>lambdaQuery()
                .eq(ProductAttributeDO::getProductId, id));
        if (CollUtil.isNotEmpty(productAttributeList)){
            // 按照属性分类id分组
            Map<Long, List<ProductAttributeDO>> attributeTypeMap = productAttributeList.stream()
                    .collect(Collectors.groupingBy(ProductAttributeDO::getAttributeTypeId, Collectors.toList()));
            List<ProductAttributeTypeDTO> params = Lists.newArrayList();
            attributeTypeMap.forEach((attributeTypeId,attributeList) ->{
                ProductAttributeTypeDTO productAttributeType = new ProductAttributeTypeDTO();
                productAttributeType.setAttributeTypeId(attributeTypeId);
                AttributeTypeDO attributeTypeDO = attributeTypeCacheService.selectById(attributeTypeId);
                productAttributeType.setAttributeShowName(attributeTypeDO.getShowName());
                productAttributeType.setType(EnumUtil.getKeyValueEnum(AttributeTypeEnum.class, attributeTypeDO.getType()));
                // 按照属性id分组
                Map<Long, List<ProductAttributeDO>> attributeMap = attributeList.stream()
                        .collect(Collectors.groupingBy(ProductAttributeDO::getAttributeId, Collectors.toList()));
                attributeMap.forEach((attributeId, attributeValueList) -> {
                    ProductAttributeDTO attributeDTO = new ProductAttributeDTO();
                    attributeDTO.setAttributeId(attributeId);
                    attributeDTO.setAttributeName(attributeCacheService.selectById(attributeId).getName());
                    List<ProductAttributeValueDTO> attributeValues = attributeValueList.stream().map(attributeValueDO -> {
                        ProductAttributeValueDTO attributeValue = new ProductAttributeValueDTO();
                        attributeValue.setAttributeValue(attributeValueDO.getAttributeValue());
                        attributeValue.setAttributePic(attributeValueDO.getPic());
                        return attributeValue;
                    }).collect(Collectors.toList());
                    attributeDTO.setAttributeValues(attributeValues);
                });
                // 设置销售规格或参数
                if (AttributeTypeEnum.SPECIFICATIONS.getCode().equals(attributeTypeDO.getType())){
                    responseDTO.setSpecifications(productAttributeType);
                }else {
                    params.add(productAttributeType);
                }
            });
            responseDTO.setParams(params);
        }
    }

}
