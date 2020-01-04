package com.dmall.pms.service.impl.support;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.api.dto.product.response.attributevalue.*;
import com.dmall.pms.api.dto.sku.response.get.SkuExtResponseDTO;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.dataobject.SkuExtDO;
import com.dmall.pms.generator.mapper.SkuExtMapper;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @description: SkuExtSupport
 * @author: created by hang.yu on 2019/12/29 15:53
 */
@Component
public class SkuExtSupport {

    private static final String PARAM_KEY = "key";

    private static final String PARAM_VALUE = "value";

    @Autowired
    private ProductAttributeValueSupport productAttributeValueSupport;

    @Autowired
    private AttributeCacheService attributeCacheService;

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private SkuExtMapper skuExtMapper;

    /**
     * 设置sku扩展表
     */
    public void setSkuExt(Long productId, Long skuId, List<Long> productAttributeValueList,
                          String detailHtml, String detailMobileHtml) {
        ProductExtResponseDTO extResponseDTO = productAttributeValueSupport.getProductAttributeValue(productId, null);
        // 规格
        JSONObject specificationsObj = new JSONObject();
        for (SpecificationsResponseDTO specification : extResponseDTO.getSpecifications()) {
            AttributeDO attributeDO = attributeCacheService.selectById(specification.getAttributeId());
            Optional<ProductAttributeValueResponseDTO> any = specification.getSpecificationsValues().stream()
                    .filter(specifications -> productAttributeValueList.contains(specifications.getProductAttributeValueId()))
                    .findAny();
            any.ifPresent(attributeValue -> specificationsObj.put(attributeDO.getShowName(), attributeValue.getAttributeValue()));
        }

        // 卖点
        JSONObject saleObj = new JSONObject();
        for (SalePointResponseDTO salePoint : extResponseDTO.getSalePoints()) {
            AttributeDO attributeDO = attributeCacheService.selectById(salePoint.getAttributeId());
            Optional<ProductAttributeValueResponseDTO> any = salePoint.getSalePointValues().stream()
                    .filter(specificationsDTO -> productAttributeValueList.contains(specificationsDTO.getProductAttributeValueId()))
                    .findAny();
            any.ifPresent(productAttributeValue -> saleObj.put(attributeDO.getShowName(), productAttributeValue.getAttributeValue()));
        }

        // 参数
        JSONArray paramArr = new JSONArray();
        for (ParamResponseDTO param : extResponseDTO.getParams()) {
            JSONObject type = new JSONObject();
            AttributeTypeDO attributeTypeDO = attributeTypeCacheService.selectById(param.getAttributeTypeId());
            JSONArray value = new JSONArray();
            for (ParamValueResponseDTO paramValue : param.getParams()) {
                AttributeDO attributeDO = attributeCacheService.selectById(paramValue.getAttributeId());
                Optional<ProductAttributeValueResponseDTO> any = paramValue.getParamValues().stream()
                        .filter(specificationsDTO -> productAttributeValueList.contains(specificationsDTO.getProductAttributeValueId()))
                        .findAny();
                if (any.isPresent()) {
                    JSONObject obj = new JSONObject();
                    obj.put(PARAM_KEY, attributeDO.getShowName());
                    obj.put(PARAM_VALUE, any.get().getAttributeValue());
                    value.add(obj);
                }
            }
            if (value.size() > 0) {
                type.put(attributeTypeDO.getShowName(), value);
                paramArr.add(type);
            }
        }
        SkuExtDO skuExtDO = getBySkuId(skuId);
        if (skuExtDO == null) {
            SkuExtDO skuExt = new SkuExtDO();
            skuExt.setSkuId(skuId);
            skuExt.setSkuSpecificationsJson(specificationsObj.toJSONString());
            skuExt.setSkuSalePointJson(saleObj.toJSONString());
            skuExt.setSkuParamJson(paramArr.toJSONString());
            skuExt.setDetailHtml(detailHtml);
            skuExt.setDetailMobileHtml(detailMobileHtml);
            skuExtMapper.insert(skuExt);
        } else {
            skuExtDO.setSkuId(skuId);
            skuExtDO.setSkuSpecificationsJson(specificationsObj.toJSONString());
            skuExtDO.setSkuSalePointJson(saleObj.toJSONString());
            skuExtDO.setSkuParamJson(paramArr.toJSONString());
            skuExtDO.setDetailMobileHtml(detailMobileHtml);
            skuExtDO.setDetailHtml(detailHtml);
            skuExtMapper.updateById(skuExtDO);
        }
    }

    /**
     * 获取sku扩展信息
     */
    public SkuExtResponseDTO getSkuExt(Long skuId) {
        SkuExtResponseDTO responseDTO = new SkuExtResponseDTO();
        SkuExtDO skuExtDO = getBySkuId(skuId);
        responseDTO.setDetailHtml(skuExtDO.getDetailHtml());
        responseDTO.setDetailMobileHtml(skuExtDO.getDetailMobileHtml());
        responseDTO.setSkuSpecificationsJson(skuExtDO.getSkuSpecificationsJson());
        responseDTO.setSkuSalePointJson(skuExtDO.getSkuSalePointJson());
        responseDTO.setSkuParamJson(skuExtDO.getSkuParamJson());
        return responseDTO;
    }

    /**
     * 根据skuId获取扩展实体
     */
    public SkuExtDO getBySkuId(Long skuId) {
        return skuExtMapper.selectOne(Wrappers.<SkuExtDO>lambdaQuery().eq(SkuExtDO::getSkuId, skuId));
    }

    /**
     * 根据skuId删除
     */
    public void deleteBySkuId(Long skuId) {
        skuExtMapper.delete(Wrappers.<SkuExtDO>lambdaQuery().eq(SkuExtDO::getSkuId, skuId));
    }

    /**
     * 根据productId删除
     */
    public void deleteByProductId(Long productId) {
        skuExtMapper.delete(Wrappers.<SkuExtDO>lambdaQuery().eq(SkuExtDO::getProductId, productId));

    }
}
