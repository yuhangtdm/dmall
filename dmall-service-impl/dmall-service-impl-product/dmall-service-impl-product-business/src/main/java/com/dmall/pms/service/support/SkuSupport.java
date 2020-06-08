package com.dmall.pms.service.support;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.util.IdGeneratorUtil;
import com.dmall.pms.api.dto.product.request.attributevalue.AddSkuRequestDTO;
import com.dmall.pms.api.dto.product.request.attributevalue.SkuSpecificationsRequestDTO;
import com.dmall.pms.api.dto.product.response.get.SkuListResponseDTO;
import com.dmall.pms.generator.dataobject.*;
import com.dmall.pms.generator.mapper.CategorySkuMapper;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.generator.mapper.SkuAttributeValueMapper;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: SkuSupport
 * @author: created by hang.yu on 2019/12/17 12:42
 */
@Component
public class SkuSupport {

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SkuExtSupport skuExtSupport;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private CategorySkuMapper categorySkuMapper;

    @Autowired
    private SkuMediaSupport skuMediaSupport;

    @Autowired
    private SkuAttributeValueSupport skuAttributeValueSupport;

    @Autowired
    private ProductAttributeValueSupport productAttributeValueSupport;

    @Autowired
    private SkuAttributeValueMapper skuAttributeValueMapper;

    /**
     * 根据商品id查询列表
     */
    public List<SkuDO> selectByProductId(Long productId) {
        return skuMapper.selectList(Wrappers.<SkuDO>lambdaQuery().eq(SkuDO::getProductId, productId));
    }

    /**
     * 新增商品的sku列表
     */
    public void saveSku(Long productId, List<Long> categoryIds, Long brandId, List<AddSkuRequestDTO> skuList) {
        for (int i = 0; i < skuList.size(); i++) {
            AddSkuRequestDTO addSkuRequestDTO = skuList.get(i);
            SkuDO skuDO = new SkuDO();
            skuDO.setId(IdGeneratorUtil.snowflakeId());
            skuDO.setProductId(productId);
            skuDO.setBrandId(brandId);
            skuDO.setPrice(addSkuRequestDTO.getPrice());
            skuDO.setStock(addSkuRequestDTO.getStock());
            skuDO.setPublishStatus(YNEnum.N.getCode());
            skuDO.setSort(i + 1);
            skuMapper.insert(skuDO);
            // skuExtDo
            for (Long categoryId : categoryIds) {
                CategoryDO categoryDO = categoryCacheService.selectById(categoryId);
                CategorySkuDO categorySkuDO = new CategorySkuDO();
                categorySkuDO.setCategoryId(categoryId);
                categorySkuDO.setProductId(productId);
                categorySkuDO.setSkuId(skuDO.getId());
                categorySkuDO.setCascadeCategoryId(categoryDO.getPath());
                categorySkuMapper.insert(categorySkuDO);
            }
            // skuAttributeValueDO
            for (SkuSpecificationsRequestDTO skuSpecification : addSkuRequestDTO.getSkuSpecifications()) {
                ProductAttributeValueDO productAttributeValueDO = productAttributeValueSupport
                    .getByProductIdAndAttributeValue(productId, skuSpecification.getAttributeId(),
                        skuSpecification.getAttributeValue());
                if (productAttributeValueDO != null) {
                    SkuAttributeValueDO skuAttributeValueDO = new SkuAttributeValueDO();
                    skuAttributeValueDO.setProductId(productId);
                    skuAttributeValueDO.setSkuId(skuDO.getId());
                    skuAttributeValueDO.setProductAttributeValueId(productAttributeValueDO.getId());
                    skuAttributeValueMapper.insert(skuAttributeValueDO);
                }
            }
        }
    }

    /**
     * 获取商品的sku
     */
    public List<SkuListResponseDTO> getSkuList(Long productId) {
        return selectByProductId(productId).stream()
            .map(skuDO -> {
                SkuListResponseDTO skuListResponseDTO = new SkuListResponseDTO();
                skuListResponseDTO.setSkuId(skuDO.getId());
                skuListResponseDTO.setSkuName(skuDO.getName());
                skuListResponseDTO.setPrice(skuDO.getPrice());
                skuListResponseDTO.setStock(skuDO.getStock());
                SkuExtDO skuExtDO = skuExtSupport.getBySkuId(skuDO.getId());
                if (skuExtDO != null) {
                    String skuSpecificationsJson = skuExtDO.getSkuSpecificationsJson();
                    JSONObject skuSpecifications = JSONObject.parseObject(skuSpecificationsJson);
                    skuListResponseDTO.setSpecifications(CollUtil.join(skuSpecifications.values(), StrUtil.COMMA));
                }
                return skuListResponseDTO;
            }).collect(Collectors.toList());
    }

    /**
     * 根据productId删除sku相关数据
     */
    public void deleteSkuByProductId(Long productId) {
        skuExtSupport.deleteByProductId(productId);
        skuMediaSupport.deleteByProductId(productId);
        skuAttributeValueSupport.deleteByProductId(productId);
        deleteByProductId(productId);
    }

    /**
     * 根据productId删除sku
     */
    public void deleteByProductId(Long productId) {
        skuMapper.delete(Wrappers.<SkuDO>lambdaQuery().eq(SkuDO::getProductId, productId));
    }

    /**
     * 根据名称获取sku
     */
    public SkuDO getByName(String name) {
        return skuMapper.selectOne(Wrappers.<SkuDO>lambdaQuery().eq(SkuDO::getName, name));
    }
}
