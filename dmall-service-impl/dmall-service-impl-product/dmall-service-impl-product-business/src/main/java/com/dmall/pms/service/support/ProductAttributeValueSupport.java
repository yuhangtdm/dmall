package com.dmall.pms.service.support;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.constants.Constants;
import com.dmall.common.enums.YNEnum;
import com.dmall.pms.api.dto.product.request.attributevalue.*;
import com.dmall.pms.api.dto.product.response.attributevalue.*;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.CategoryProductDO;
import com.dmall.pms.generator.dataobject.ProductAttributeValueDO;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.mapper.CategoryProductMapper;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.generator.service.IProductAttributeValueService;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description: 商品属性值处理类
 * @author: created by hang.yu on 2019/12/27 21:05
 */
@Component
public class ProductAttributeValueSupport {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private IProductAttributeValueService iProductAttributeValueService;

    @Autowired
    private AttributeCacheService attributeCacheService;

    @Autowired
    private CategoryProductMapper categoryProductMapper;

    private static final String VALUE = "value";

    private static final String PIC = "pic";

    /**
     * 保存商品属性值
     */
    public void saveProductAttributeValue(ProductDO productDO, ProductAttributeRequestDTO productExtRequestDTO) {
        List<ProductAttributeValueDO> list = Lists.newArrayList();
        Long productId = productDO.getId();
        // 规格
        JSONObject productSpecifications = new JSONObject();
        List<SpecificationsRequestDTO> specifications = productExtRequestDTO.getSpecifications();
        for (SpecificationsRequestDTO specification : specifications) {
            JSONArray jsonArray = new JSONArray();
            for (SpecificationsValueRequestDTO specificationsValue : specification.getSpecificationsValues()) {
                ProductAttributeValueDO attributeValueDO = new ProductAttributeValueDO();
                attributeValueDO.setProductId(productId);
                attributeValueDO.setAttributeId(specification.getAttributeId());
                attributeValueDO.setIsSellingPoint(YNEnum.N.getCode());
                attributeValueDO.setIsSpecifications(YNEnum.Y.getCode());
                attributeValueDO.setIsParam(YNEnum.N.getCode());
                attributeValueDO.setAttributeValue(specificationsValue.getAttributeValue());
                attributeValueDO.setPic(specificationsValue.getPic());
                if (!list.contains(attributeValueDO)) {
                    list.add(attributeValueDO);
                } else {
                    ProductAttributeValueDO productAttributeValueDO = find(list, specification.getAttributeId(),
                            productId, specificationsValue.getAttributeValue());
                    productAttributeValueDO.setIsSpecifications(YNEnum.Y.getCode());
                }
                JSONObject object = new JSONObject();
                object.put(VALUE, specificationsValue.getAttributeValue());
                if (StrUtil.isNotBlank(specificationsValue.getPic())) {
                    object.put(PIC, specificationsValue.getPic());
                }
                jsonArray.add(object);
            }
            AttributeDO attributeDO = attributeCacheService.selectById(specification.getAttributeId());
            productSpecifications.put(attributeDO.getShowName(), jsonArray);
        }
        // 卖点
        List<SalePointRequestDTO> salePoints = productExtRequestDTO.getSalePoints();
        for (SalePointRequestDTO salePoint : salePoints) {
            for (String salePointValue : salePoint.getSalePointValues()) {
                ProductAttributeValueDO attributeValueDO = new ProductAttributeValueDO();
                attributeValueDO.setAttributeId(salePoint.getAttributeId());
                attributeValueDO.setProductId(productId);
                attributeValueDO.setIsSellingPoint(YNEnum.Y.getCode());
                attributeValueDO.setIsSpecifications(YNEnum.N.getCode());
                attributeValueDO.setIsParam(YNEnum.N.getCode());
                attributeValueDO.setAttributeValue(salePointValue);
                if (!list.contains(attributeValueDO)) {
                    list.add(attributeValueDO);
                } else {
                    ProductAttributeValueDO productAttributeValueDO = find(list, salePoint.getAttributeId(), productId, salePointValue);
                    productAttributeValueDO.setIsSellingPoint(YNEnum.Y.getCode());
                }
            }
        }
        // 参数
        List<ParamRequestDTO> params = productExtRequestDTO.getParams();
        for (ParamRequestDTO param : params) {
            for (ParamValueRequestDTO paramAttribute : param.getParamAttributes()) {
                for (String paramValue : paramAttribute.getParamValues()) {
                    ProductAttributeValueDO attributeValueDO = new ProductAttributeValueDO();
                    attributeValueDO.setProductId(productId);
                    attributeValueDO.setAttributeTypeId(param.getAttributeTypeId());
                    attributeValueDO.setAttributeId(paramAttribute.getAttributeId());
                    attributeValueDO.setIsSellingPoint(YNEnum.N.getCode());
                    attributeValueDO.setIsSpecifications(YNEnum.N.getCode());
                    attributeValueDO.setIsParam(YNEnum.Y.getCode());
                    attributeValueDO.setAttributeValue(paramValue);
                    if (!list.contains(attributeValueDO)) {
                        list.add(attributeValueDO);
                    } else {
                        ProductAttributeValueDO productAttributeValueDO = find(list, paramAttribute.getAttributeId(), productId, paramValue);
                        productAttributeValueDO.setIsParam(YNEnum.Y.getCode());
                    }
                }
            }
        }
        // 先根据商品id删除
        deleteByProductId(productId);
        // 保存商品属性值
        iProductAttributeValueService.saveBatch(list);
        // 修改商品的规格
        productDO.setSpecificationsJson(productSpecifications.toJSONString());
        productMapper.updateById(productDO);
    }

    /**
     * 获取商品属性值
     */
    public ProductExtResponseDTO getProductAttributeValue(Long productId, Long brandId) {
        List<ProductAttributeValueDO> list = iProductAttributeValueService.list(Wrappers.<ProductAttributeValueDO>lambdaQuery()
                .eq(ProductAttributeValueDO::getProductId, productId));
        List<SpecificationsResponseDTO> specifications = Lists.newArrayList();
        List<SalePointResponseDTO> salePoints = Lists.newArrayList();
        List<ParamResponseDTO> params = Lists.newArrayList();
        // 规格
        Map<Long, List<ProductAttributeValueDO>> specificationsMap = list.stream()
                .filter(productAttributeValue -> Constants.Y.equals(productAttributeValue.getIsSpecifications()))
                .collect(Collectors.groupingBy(ProductAttributeValueDO::getAttributeId));
        specificationsMap.forEach((k, v) -> {
            SpecificationsResponseDTO specificationsResponseDTO = new SpecificationsResponseDTO();
            specificationsResponseDTO.setAttributeId(k);
            List<ProductAttributeValueResponseDTO> specificationsValues = v.stream().map(productAttributeValue -> {
                ProductAttributeValueResponseDTO attributeValueResponseDTO = new ProductAttributeValueResponseDTO();
                attributeValueResponseDTO.setProductAttributeValueId(productAttributeValue.getId());
                attributeValueResponseDTO.setAttributeValue(productAttributeValue.getAttributeValue());
                attributeValueResponseDTO.setPic(productAttributeValue.getPic());
                return attributeValueResponseDTO;
            }).collect(Collectors.toList());
            specificationsResponseDTO.setSpecificationsValues(specificationsValues);
            specifications.add(specificationsResponseDTO);
        });
        // 卖点
        Map<Long, List<ProductAttributeValueDO>> salePointMap = list.stream()
                .filter(productAttributeValue -> Constants.Y.equals(productAttributeValue.getIsSellingPoint()))
                .collect(Collectors.groupingBy(ProductAttributeValueDO::getAttributeId));
        salePointMap.forEach((k, v) -> {
            SalePointResponseDTO salePointResponseDTO = new SalePointResponseDTO();
            salePointResponseDTO.setAttributeId(k);
            List<ProductAttributeValueResponseDTO> salePointValues = v.stream().map(productAttributeValue -> {
                ProductAttributeValueResponseDTO attributeValueResponseDTO = new ProductAttributeValueResponseDTO();
                attributeValueResponseDTO.setProductAttributeValueId(productAttributeValue.getId());
                attributeValueResponseDTO.setAttributeValue(productAttributeValue.getAttributeValue());
                return attributeValueResponseDTO;
            }).collect(Collectors.toList());
            salePointResponseDTO.setSalePointValues(salePointValues);
            salePoints.add(salePointResponseDTO);
        });
        // 参数
        Map<Long, List<ProductAttributeValueDO>> paramMap = list.stream()
                .filter(productAttributeValue -> Constants.Y.equals(productAttributeValue.getIsParam()))
                .collect(Collectors.groupingBy(ProductAttributeValueDO::getAttributeTypeId));
        paramMap.forEach((k, v) -> {
            List<ParamValueResponseDTO> paramValueResponseDTOS = Lists.newArrayList();
            Map<Long, List<ProductAttributeValueDO>> paramValueMap = v.stream()
                    .collect(Collectors.groupingBy(ProductAttributeValueDO::getAttributeId));
            paramValueMap.forEach((key, value) -> {
                ParamValueResponseDTO paramValueResponseDTO = new ParamValueResponseDTO();
                paramValueResponseDTO.setAttributeId(key);
                List<ProductAttributeValueResponseDTO> salePointValues = value.stream().map(productAttributeValue -> {
                    ProductAttributeValueResponseDTO attributeValueResponseDTO = new ProductAttributeValueResponseDTO();
                    attributeValueResponseDTO.setAttributeValue(productAttributeValue.getAttributeValue());
                    attributeValueResponseDTO.setProductAttributeValueId(productAttributeValue.getId());
                    return attributeValueResponseDTO;
                }).collect(Collectors.toList());
                paramValueResponseDTO.setParamValues(salePointValues);
                paramValueResponseDTOS.add(paramValueResponseDTO);
            });
            ParamResponseDTO paramResponseDTO = new ParamResponseDTO();
            paramResponseDTO.setAttributeTypeId(k);
            paramResponseDTO.setParams(paramValueResponseDTOS);
            params.add(paramResponseDTO);
        });
        List<Long> categoryIds = categoryProductMapper.selectList(Wrappers.<CategoryProductDO>lambdaQuery()
                .eq(CategoryProductDO::getProductId, productId)).stream()
                .map(CategoryProductDO::getCategoryId)
                .collect(Collectors.toList());
        ProductExtResponseDTO responseDTO = new ProductExtResponseDTO();
        responseDTO.setCategoryIds(categoryIds);
        responseDTO.setBrandId(brandId);
        responseDTO.setSpecifications(specifications);
        responseDTO.setSalePoints(salePoints);
        responseDTO.setParams(params);
        return responseDTO;
    }

    /**
     * 根据属性类别id查询列表
     */
    public List<ProductAttributeValueDO> listByAttributeTypeId(Long attributeTypeId) {
        return iProductAttributeValueService.list(Wrappers.<ProductAttributeValueDO>lambdaQuery()
                .eq(ProductAttributeValueDO::getAttributeTypeId, attributeTypeId));
    }

    /**
     * 根据属性id查询列表
     */
    public List<ProductAttributeValueDO> listByAttributeId(Long attributeId) {
        return iProductAttributeValueService.list(Wrappers.<ProductAttributeValueDO>lambdaQuery()
                .eq(ProductAttributeValueDO::getAttributeId, attributeId));
    }

    /**
     * 根据商品id删除
     */
    public void deleteByProductId(Long productId) {
        iProductAttributeValueService.remove(Wrappers.<ProductAttributeValueDO>lambdaQuery()
                .eq(ProductAttributeValueDO::getProductId, productId));
    }

    /**
     * 根据商品id，属性id，属性值 查询实体
     */
    public ProductAttributeValueDO getByProductIdAndAttributeValue(Long productId, Long attributeId, String attributeValue) {
        return iProductAttributeValueService.getOne(Wrappers.<ProductAttributeValueDO>lambdaQuery()
                .eq(ProductAttributeValueDO::getProductId, productId)
                .eq(ProductAttributeValueDO::getAttributeId, attributeId)
                .eq(ProductAttributeValueDO::getAttributeValue, attributeValue));
    }

    private ProductAttributeValueDO find(List<ProductAttributeValueDO> list, Long attributeId,
                                         Long productId, String attributeValue) {
        Optional<ProductAttributeValueDO> any = list.stream()
                .filter(productAttributeValueDO -> productAttributeValueDO.getAttributeId().equals(attributeId)
                        && productAttributeValueDO.getProductId().equals(productId)
                        && productAttributeValueDO.getAttributeValue().equals(attributeValue)).findAny();
        return any.orElse(null);
    }
}
