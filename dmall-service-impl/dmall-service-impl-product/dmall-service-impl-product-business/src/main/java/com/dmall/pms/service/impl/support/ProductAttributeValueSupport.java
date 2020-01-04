package com.dmall.pms.service.impl.support;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.constants.Constants;
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

    /**
     * 保存商品属性值
     */
    public void saveProductAttributeValue(ProductDO productDO, ProductExtRequestDTO productExtRequestDTO) {
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
                attributeValueDO.setIsSellingPoint("N");
                attributeValueDO.setIsSpecifications("Y");
                attributeValueDO.setIsParam("N");
                attributeValueDO.setAttributeValue(specificationsValue.getAttributeValue());
                attributeValueDO.setPic(specificationsValue.getPic());
                list.add(attributeValueDO);
                JSONObject object = new JSONObject();
                object.put("value", specificationsValue.getAttributeValue());
                if (StrUtil.isNotBlank(specificationsValue.getPic())) {
                    object.put("pic", specificationsValue.getPic());
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
                attributeValueDO.setIsSellingPoint("Y");
                attributeValueDO.setIsSpecifications("N");
                attributeValueDO.setIsParam("N");
                attributeValueDO.setAttributeValue(salePointValue);
                list.add(attributeValueDO);
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
                    attributeValueDO.setIsSellingPoint("N");
                    attributeValueDO.setIsSpecifications("N");
                    attributeValueDO.setIsParam("Y");
                    attributeValueDO.setAttributeValue(paramValue);
                    list.add(attributeValueDO);
                }
            }
        }

        // 保存商品属性值
        deleteByProductId(productId);
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
     * 根据属性分类id查询列表
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
}
