package com.dmall.pms.service.impl.sku.handler;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.product.response.attributevalue.*;
import com.dmall.pms.api.dto.sku.request.save.SaveSkuAttributeRequestDTO;
import com.dmall.pms.generator.dataobject.*;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.generator.mapper.SkuExtMapper;
import com.dmall.pms.generator.service.ISkuAttributeValueService;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.impl.product.common.ProductAttributeValueSupport;
import com.dmall.pms.service.impl.sku.enums.SkuErrorEnum;
import com.dmall.pms.service.impl.sku.support.SkuSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description: SaveSkuAttributeHandler
 * @author: created by hang.yu on 2019/12/16 16:43
 */
@Component
public class SaveSkuAttributeHandler extends AbstractCommonHandler<SaveSkuAttributeRequestDTO, SkuAttributeValueDO, Long> {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SkuSupport skuSupport;

    @Autowired
    private ISkuAttributeValueService iSkuAttributeValueService;

    @Autowired
    private SkuExtMapper skuExtMapper;

    @Autowired
    private ProductAttributeValueSupport productAttributeValueSupport;

    @Autowired
    private AttributeCacheService attributeCacheService;

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    private static final String PARAM_KEY = "key";

    private static final String PARAM_VALUE = "value";


    @Override
    public BaseResult<Long> validate(SaveSkuAttributeRequestDTO requestDTO) {
        // 商品必须存在
        ProductDO productDO = productMapper.selectById(requestDTO.getProductId());
        if (productDO == null) {
            return ResultUtil.fail(SkuErrorEnum.PRODUCT_NOT_EXISTS);
        }
        return skuSupport.validate(requestDTO.getSkuId());
    }

    @Override
    public BaseResult<Long> processor(SaveSkuAttributeRequestDTO requestDTO) {
        Long productId = requestDTO.getProductId();
        Long skuId = requestDTO.getSkuId();
        List<Long> productAttributeValueList = requestDTO.getProductAttributeValueList();
        // 设置sku属性值
        setSkuAttributeValue(productId, skuId, productAttributeValueList);
        // 设置sku扩展表
        setSkuExt(productId, skuId, productAttributeValueList);
        return ResultUtil.success(skuId);
    }

    /**
     * 设置sku属性值
     */
    private void setSkuAttributeValue(Long productId, Long skuId, List<Long> productAttributeValueList) {
        List<SkuAttributeValueDO> oldList = iSkuAttributeValueService.list(Wrappers.<SkuAttributeValueDO>lambdaQuery()
                .eq(SkuAttributeValueDO::getSkuId, skuId));

        if (CollUtil.isNotEmpty(oldList)) {
            List<SkuAttributeValueDO> skuAttributeList = productAttributeValueList.stream()
                    .map(productAttributeValueId -> {
                        SkuAttributeValueDO skuAttributeValueDO = new SkuAttributeValueDO();
                        skuAttributeValueDO.setProductId(productId);
                        skuAttributeValueDO.setSkuId(skuId);
                        skuAttributeValueDO.setProductAttributeValueId(productAttributeValueId);
                        return skuAttributeValueDO;
                    }).collect(Collectors.toList());
            iSkuAttributeValueService.saveBatch(skuAttributeList);
        } else {
            List<Long> oldAttributeValueIds = oldList.stream().map(SkuAttributeValueDO::getProductAttributeValueId)
                    .collect(Collectors.toList());
            // 新增的id集合
            List<Long> insertIdList = productAttributeValueList.stream()
                    .filter(attributeValueId -> !oldAttributeValueIds.contains(attributeValueId))
                    .collect(Collectors.toList());
            // 删除的id集合
            List<Long> deleteIdList = oldAttributeValueIds.stream()
                    .filter(oldBrandId -> !productAttributeValueList.contains(oldBrandId))
                    .collect(Collectors.toList());

            if (CollUtil.isNotEmpty(insertIdList)) {
                List<SkuAttributeValueDO> insertList = insertIdList.stream().map(productAttributeValueId -> {
                    SkuAttributeValueDO skuAttributeValueDO = new SkuAttributeValueDO();
                    skuAttributeValueDO.setSkuId(skuId);
                    skuAttributeValueDO.setProductId(productId);
                    skuAttributeValueDO.setProductAttributeValueId(productAttributeValueId);
                    return skuAttributeValueDO;
                }).collect(Collectors.toList());
                iSkuAttributeValueService.saveBatch(insertList);
            }
            if (CollUtil.isNotEmpty(deleteIdList)) {
                iSkuAttributeValueService.remove(Wrappers.<SkuAttributeValueDO>lambdaQuery()
                        .in(SkuAttributeValueDO::getProductAttributeValueId, deleteIdList));
            }
        }
    }

    /**
     * 设置sku扩展表
     */
    private void setSkuExt(Long productId, Long skuId, List<Long> productAttributeValueList) {
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
        SkuExtDO skuExtDO = skuExtMapper.selectOne(Wrappers.<SkuExtDO>lambdaQuery().eq(SkuExtDO::getSkuId, skuId));
        if (skuExtDO == null) {
            SkuExtDO skuExt = new SkuExtDO();
            skuExt.setSkuId(skuId);
            skuExt.setSkuSpecificationsJson(specificationsObj.toJSONString());
            skuExt.setSkuSalePointJson(saleObj.toJSONString());
            skuExt.setSkuParamJson(paramArr.toJSONString());
            skuExtMapper.insert(skuExt);
        } else {
            skuExtDO.setSkuId(skuId);
            skuExtDO.setSkuSpecificationsJson(specificationsObj.toJSONString());
            skuExtDO.setSkuSalePointJson(saleObj.toJSONString());
            skuExtDO.setSkuParamJson(paramArr.toJSONString());
            skuExtMapper.updateById(skuExtDO);
        }
    }

}
