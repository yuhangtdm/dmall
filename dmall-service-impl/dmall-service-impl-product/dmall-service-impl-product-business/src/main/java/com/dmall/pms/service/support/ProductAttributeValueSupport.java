package com.dmall.pms.service.support;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.constants.Constants;
import com.dmall.common.enums.YNEnum;
import com.dmall.pms.api.dto.product.request.attributevalue.ParamValueRequestDTO;
import com.dmall.pms.api.dto.product.request.attributevalue.ProductAttributeRequestDTO;
import com.dmall.pms.api.dto.product.request.attributevalue.SalePointRequestDTO;
import com.dmall.pms.api.dto.product.request.attributevalue.SpecificationsRequestDTO;
import com.dmall.pms.api.dto.product.response.attributevalue.ParamResponseDTO;
import com.dmall.pms.api.dto.product.response.attributevalue.ProductAttributeResponseDTO;
import com.dmall.pms.api.dto.product.response.attributevalue.ProductAttributeValueResponseDTO;
import com.dmall.pms.api.dto.product.response.attributevalue.ProductExtResponseDTO;
import com.dmall.pms.generator.dataobject.*;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.generator.service.IProductAttributeValueService;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.impl.brand.cache.BrandCacheService;
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
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private CategoryProductSupport categoryProductSupport;

    @Autowired
    private BrandCacheService brandCacheService;

    @Autowired
    private CategorySupport categorySupport;

    private static final String VALUE = "value";

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
            for (String specificationsValue : specification.getAttributeValues()) {
                ProductAttributeValueDO attributeValueDO = new ProductAttributeValueDO();
                attributeValueDO.setProductId(productId);
                attributeValueDO.setAttributeId(specification.getAttributeId());
                attributeValueDO.setIsSellingPoint(YNEnum.N.getCode());
                attributeValueDO.setIsSpecifications(YNEnum.Y.getCode());
                attributeValueDO.setIsParam(YNEnum.N.getCode());
                attributeValueDO.setAttributeValue(specificationsValue);
                if (!list.contains(attributeValueDO)) {
                    list.add(attributeValueDO);
                } else {
                    ProductAttributeValueDO productAttributeValueDO = find(list, specification.getAttributeId(),
                        productId, specificationsValue);
                    if (productAttributeValueDO != null) {
                        productAttributeValueDO.setIsSpecifications(YNEnum.Y.getCode());
                    }
                }
                JSONObject object = new JSONObject();
                object.put(VALUE, specificationsValue);
                jsonArray.add(object);
            }
            AttributeDO attributeDO = attributeCacheService.selectById(specification.getAttributeId());
            productSpecifications.put(attributeDO.getShowName(), jsonArray);
        }
        // 卖点
        List<SalePointRequestDTO> salePoints = productExtRequestDTO.getSalePoints();
        for (SalePointRequestDTO salePoint : salePoints) {
            for (String salePointValue : salePoint.getAttributeValues()) {
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
                    ProductAttributeValueDO productAttributeValueDO =
                        find(list, salePoint.getAttributeId(), productId, salePointValue);
                    if (productAttributeValueDO != null) {
                        productAttributeValueDO.setIsSellingPoint(YNEnum.Y.getCode());
                    }
                }
            }
        }
        // 参数
        List<ParamValueRequestDTO> params = productExtRequestDTO.getParams();
        for (ParamValueRequestDTO param : params) {
            for (String paramValue : param.getAttributeValues()) {
                ProductAttributeValueDO attributeValueDO = new ProductAttributeValueDO();
                attributeValueDO.setProductId(productId);
                attributeValueDO.setAttributeTypeId(param.getAttributeTypeId());
                attributeValueDO.setAttributeId(param.getAttributeId());
                attributeValueDO.setIsSellingPoint(YNEnum.N.getCode());
                attributeValueDO.setIsSpecifications(YNEnum.N.getCode());
                attributeValueDO.setIsParam(YNEnum.Y.getCode());
                attributeValueDO.setAttributeValue(paramValue);
                if (!list.contains(attributeValueDO)) {
                    list.add(attributeValueDO);
                } else {
                    ProductAttributeValueDO productAttributeValueDO =
                        find(list, param.getAttributeId(), productId, paramValue);
                    if (productAttributeValueDO != null) {
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
        List<ProductAttributeValueDO> list = listByProductId(productId);
        List<ProductAttributeResponseDTO> specifications = Lists.newArrayList();
        List<ProductAttributeResponseDTO> salePoints = Lists.newArrayList();
        List<ParamResponseDTO> params = Lists.newArrayList();
        // 规格
        buildSpecifications(list, specifications);
        // 卖点
        buildSalePoints(list, salePoints);
        // 参数
        buildParams(list, params);
        List<Long> categoryIds = categoryProductSupport.listByProductId(productId).stream()
            .map(CategoryProductDO::getCategoryId)
            .collect(Collectors.toList());
        List<String> cascadeCategoryNames = categoryIds.stream()
            .map(categoryId -> categorySupport.getCascadeCategoryName(categoryId)).collect(Collectors.toList());

        ProductExtResponseDTO response = new ProductExtResponseDTO();
        response.setCategoryIds(categoryIds);
        response.setCascadeCategoryNames(cascadeCategoryNames);
        response.setBrandId(brandId);
        BrandDO brandDO = brandCacheService.selectById(brandId);
        response.setBrandName(brandDO.getName());
        response.setSpecifications(specifications);
        response.setSalePoints(salePoints);
        response.setParams(params);
        return response;
    }

    private void buildParams(List<ProductAttributeValueDO> list, List<ParamResponseDTO> params) {
        Map<Long, List<ProductAttributeValueDO>> paramMap = list.stream()
            .filter(productAttributeValue -> Constants.Y.equals(productAttributeValue.getIsParam()))
            .collect(Collectors.groupingBy(ProductAttributeValueDO::getAttributeTypeId));
        paramMap.forEach((k, v) -> {
            List<ProductAttributeResponseDTO> paramValueResponseList = Lists.newArrayList();
            Map<Long, List<ProductAttributeValueDO>> paramValueMap = v.stream()
                .collect(Collectors.groupingBy(ProductAttributeValueDO::getAttributeId));
            paramValueMap.forEach((key, value) -> {
                ProductAttributeResponseDTO paramValueResponse = new ProductAttributeResponseDTO();
                paramValueResponse.setAttributeId(key);
                AttributeDO attributeDO = attributeCacheService.selectById(key);
                paramValueResponse.setAttributeName(attributeDO.getShowName());
                List<ProductAttributeValueResponseDTO> paramValues = value.stream().map(productAttributeValue -> {
                    ProductAttributeValueResponseDTO attributeValueResponse = new ProductAttributeValueResponseDTO();
                    attributeValueResponse.setAttributeValue(productAttributeValue.getAttributeValue());
                    attributeValueResponse.setProductAttributeValueId(productAttributeValue.getId());
                    return attributeValueResponse;
                }).collect(Collectors.toList());
                paramValueResponse.setAttributeValues(paramValues);
                paramValueResponseList.add(paramValueResponse);
            });
            ParamResponseDTO paramResponse = new ParamResponseDTO();
            paramResponse.setAttributeTypeId(k);
            AttributeTypeDO attributeTypeDO = attributeTypeCacheService.selectById(k);
            paramResponse.setAttributeTypeName(attributeTypeDO.getShowName());
            paramResponse.setParams(paramValueResponseList);
            params.add(paramResponse);
        });
    }

    private void buildSalePoints(List<ProductAttributeValueDO> list, List<ProductAttributeResponseDTO> salePoints) {
        Map<Long, List<ProductAttributeValueDO>> salePointMap = list.stream()
            .filter(productAttributeValue -> Constants.Y.equals(productAttributeValue.getIsSellingPoint()))
            .collect(Collectors.groupingBy(ProductAttributeValueDO::getAttributeId));
        salePointMap.forEach((k, v) -> {
            ProductAttributeResponseDTO salePointResponse = new ProductAttributeResponseDTO();
            AttributeDO attributeDO = attributeCacheService.selectById(k);
            salePointResponse.setAttributeId(k);
            salePointResponse.setAttributeName(attributeDO.getShowName());
            List<ProductAttributeValueResponseDTO> salePointValues = v.stream().map(productAttributeValue -> {
                ProductAttributeValueResponseDTO attributeValueResponse = new ProductAttributeValueResponseDTO();
                attributeValueResponse.setProductAttributeValueId(productAttributeValue.getId());
                attributeValueResponse.setAttributeValue(productAttributeValue.getAttributeValue());
                return attributeValueResponse;
            }).collect(Collectors.toList());
            salePointResponse.setAttributeValues(salePointValues);
            salePoints.add(salePointResponse);
        });
    }

    private void buildSpecifications(List<ProductAttributeValueDO> list,
        List<ProductAttributeResponseDTO> specifications) {
        Map<Long, List<ProductAttributeValueDO>> specificationsMap = list.stream()
            .filter(productAttributeValue -> Constants.Y.equals(productAttributeValue.getIsSpecifications()))
            .collect(Collectors.groupingBy(ProductAttributeValueDO::getAttributeId));
        specificationsMap.forEach((k, v) -> {
            ProductAttributeResponseDTO productAttributeResponse = new ProductAttributeResponseDTO();
            productAttributeResponse.setAttributeId(k);
            AttributeDO attributeDO = attributeCacheService.selectById(k);
            productAttributeResponse.setAttributeName(attributeDO.getShowName());
            List<ProductAttributeValueResponseDTO> specificationsValues = v.stream().map(productAttributeValue -> {
                ProductAttributeValueResponseDTO attributeValueResponse = new ProductAttributeValueResponseDTO();
                attributeValueResponse.setProductAttributeValueId(productAttributeValue.getId());
                attributeValueResponse.setAttributeValue(productAttributeValue.getAttributeValue());
                attributeValueResponse.setPic(productAttributeValue.getPic());
                return attributeValueResponse;
            }).collect(Collectors.toList());
            productAttributeResponse.setAttributeValues(specificationsValues);
            specifications.add(productAttributeResponse);
        });
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
     * 根据商品id查询列表
     */
    public List<ProductAttributeValueDO> listByProductId(Long productId) {
        return iProductAttributeValueService.list(Wrappers.<ProductAttributeValueDO>lambdaQuery()
            .eq(ProductAttributeValueDO::getProductId, productId));
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
    public ProductAttributeValueDO getByProductIdAndAttributeValue(Long productId, Long attributeId,
        String attributeValue) {
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
                && productAttributeValueDO.getAttributeValue().equals(attributeValue))
            .findAny();
        return any.orElse(null);
    }
}
