package com.dmall.pms.service.support;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.api.dto.sku.response.get.SkuAttributeValueResponseDTO;
import com.dmall.pms.generator.dataobject.SkuAttributeValueDO;
import com.dmall.pms.generator.service.ISkuAttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: SkuAttributeValueSupport
 * @author: created by hang.yu on 2019/12/29 15:51
 */
@Component
public class SkuAttributeValueSupport {

    @Autowired
    private ISkuAttributeValueService iSkuAttributeValueService;

    @Autowired
    private CategorySkuSupport categorySkuSupport;

    /**
     * 设置sku属性值
     * 删除+新增 sku属性值
     */
    public void setSkuAttributeValue(Long productId, Long skuId, List<Long> productAttributeValueList) {
        List<SkuAttributeValueDO> oldList = listBySkuId(skuId);
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
                    .filter(attributeValueId -> !productAttributeValueList.contains(attributeValueId))
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
     * 获取sku属性值
     */
    public SkuAttributeValueResponseDTO getSkuAttributeValue(Long skuId, Long brandId) {
        SkuAttributeValueResponseDTO responseDTO = new SkuAttributeValueResponseDTO();
        responseDTO.setCategoryIds(categorySkuSupport.getCategoryIds(skuId));
        responseDTO.setBrandId(brandId);
        responseDTO.setProductAttributeValueList(listBySkuId(skuId).stream()
                .map(SkuAttributeValueDO::getId).collect(Collectors.toList()));
        return responseDTO;
    }

    /**
     * 根据skuId查询属性值列表
     */
    public List<SkuAttributeValueDO> listBySkuId(Long skuId) {
        return iSkuAttributeValueService.list(Wrappers.<SkuAttributeValueDO>lambdaQuery()
                .eq(SkuAttributeValueDO::getSkuId, skuId));
    }

    /**
     * 根据productId查询属性值列表
     */
    public List<SkuAttributeValueDO> listByProductId(Long productId) {
        return iSkuAttributeValueService.list(Wrappers.<SkuAttributeValueDO>lambdaQuery()
                .eq(SkuAttributeValueDO::getProductId, productId));
    }

    /**
     * 根据skuId删除
     */
    public void deleteBySkuId(Long skuId) {
        iSkuAttributeValueService.remove(Wrappers.<SkuAttributeValueDO>lambdaQuery()
                .eq(SkuAttributeValueDO::getSkuId, skuId));
    }

    /**
     * 根据productId删除
     */
    public void deleteByProductId(Long productId) {
        iSkuAttributeValueService.remove(Wrappers.<SkuAttributeValueDO>lambdaQuery()
                .eq(SkuAttributeValueDO::getProductId, productId));
    }
}
