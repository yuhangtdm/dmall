package com.dmall.pms.service.impl.sku.handler;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.product.response.GetProductAttributeResponseDTO;
import com.dmall.pms.api.dto.sku.request.save.SaveSkuAttributeRequestDTO;
import com.dmall.pms.generator.dataobject.SkuAttributeDO;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.dataobject.SkuExtDO;
import com.dmall.pms.generator.mapper.SkuExtMapper;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.dmall.pms.generator.service.ISkuAttributeService;
import com.dmall.pms.service.impl.product.attribute.ProductAttributeSupport;
import com.dmall.pms.service.impl.product.common.ProductValidate;
import com.dmall.pms.service.impl.sku.enums.SkuErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: SaveSkuAttributeHandler
 * @author: created by hang.yu on 2019/12/16 16:43
 */
@Component
public class SaveSkuAttributeHandler extends AbstractCommonHandler<SaveSkuAttributeRequestDTO, SkuAttributeDO, Long> {

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private ProductValidate productValidate;

    @Autowired
    private ISkuAttributeService iSkuAttributeService;

    @Autowired
    private ProductAttributeSupport productAttributeSupport;

    @Autowired
    private SkuExtMapper skuExtMapper;


    @Override
    public BaseResult validate(SaveSkuAttributeRequestDTO requestDTO) {
        SkuDO skuDO = skuMapper.selectById(requestDTO.getSkuId());
        if (skuDO == null) {
            return ResultUtil.fail(SkuErrorEnum.SKU_NOT_EXIST);
        }
        return productValidate.basicValidate(requestDTO.getCategoryId(), requestDTO.getBrandId());
    }

    @Override
    public BaseResult processor(SaveSkuAttributeRequestDTO requestDTO) {
        return setProductAttribute(requestDTO.getSkuId(), requestDTO.getAttributeValueList());
    }

    public BaseResult setProductAttribute(Long skuId, List<Long> attributeValueList) {
        List<SkuAttributeDO> oldList = iSkuAttributeService.list(Wrappers.<SkuAttributeDO>lambdaQuery()
                .eq(SkuAttributeDO::getSkuId, skuId));

        if (CollUtil.isNotEmpty(oldList)) {
            List<SkuAttributeDO> skuAttributeList = attributeValueList.stream().map(productAttributeId -> {
                SkuAttributeDO skuAttributeDO = new SkuAttributeDO();
                skuAttributeDO.setProductAttributeId(productAttributeId);
                skuAttributeDO.setSkuId(skuId);
                return skuAttributeDO;
            }).collect(Collectors.toList());
            iSkuAttributeService.saveBatch(skuAttributeList);
        } else {
            List<Long> oldAttributeValueIds = oldList.stream().map(SkuAttributeDO::getProductAttributeId)
                    .collect(Collectors.toList());
            // 新增的id集合
            List<Long> insertIdList = attributeValueList.stream()
                    .filter(attributeValueId -> !oldAttributeValueIds.contains(attributeValueId))
                    .collect(Collectors.toList());
            // 删除的id集合
            List<Long> deleteIdList = oldAttributeValueIds.stream()
                    .filter(oldBrandId -> !attributeValueList.contains(oldBrandId))
                    .collect(Collectors.toList());

            if (CollUtil.isNotEmpty(insertIdList)) {
                List<SkuAttributeDO> insertList = insertIdList.stream().map(productAttributeId -> {
                    SkuAttributeDO skuAttributeDO = new SkuAttributeDO();
                    skuAttributeDO.setSkuId(skuId);
                    skuAttributeDO.setProductAttributeId(productAttributeId);
                    return skuAttributeDO;
                }).collect(Collectors.toList());
                iSkuAttributeService.saveBatch(insertList);
            }
            if (CollUtil.isNotEmpty(deleteIdList)) {
                iSkuAttributeService.remove(Wrappers.<SkuAttributeDO>lambdaQuery().in(SkuAttributeDO::getProductAttributeId, deleteIdList));
            }
        }
        SkuDO skuDO = skuMapper.selectById(skuId);
        // 设置json到 sku_ext
        GetProductAttributeResponseDTO responseDTO = productAttributeSupport.setSaleAttribute(skuDO.getProductId());
        changeProductAttribute(responseDTO, attributeValueList);
        String json = JSONObject.toJSONString(responseDTO);
        SkuExtDO skuExtDO = skuExtMapper.selectOne(Wrappers.<SkuExtDO>lambdaQuery().eq(SkuExtDO::getSkuId, skuId));
        if (skuExtDO == null) {
            SkuExtDO skuExt = new SkuExtDO();
            skuExt.setSkuId(skuId);
//            skuExt.setSkuAttributeJson(json);
            skuExtMapper.insert(skuExt);
        } else {
//            skuExtDO.setSkuAttributeJson(json);
            skuExtMapper.updateById(skuExtDO);
        }
        return ResultUtil.success(skuId);
    }

    public void changeProductAttribute(GetProductAttributeResponseDTO responseDTO, List<Long> attributeValueList) {
        responseDTO.getSpecifications().getAttributes().forEach(attribute -> {
            attribute.getAttributeValues().
                    removeIf(attributeValue -> !attributeValueList
                            .contains(attributeValue.getProductAttributeValueId()));
        });
        responseDTO.getParams().forEach(attributeType -> {
            attributeType.getAttributes().forEach(attribute -> {
                attribute.getAttributeValues().
                        removeIf(attributeValue -> !attributeValueList
                                .contains(attributeValue.getProductAttributeValueId()));
            });
        });
    }
}
