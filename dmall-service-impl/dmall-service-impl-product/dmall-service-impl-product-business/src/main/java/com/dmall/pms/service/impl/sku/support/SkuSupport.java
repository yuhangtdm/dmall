package com.dmall.pms.service.impl.sku.support;

import java.math.BigDecimal;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.util.NoUtil;
import com.dmall.pms.api.dto.product.request.attributevalue.AddSkuRequestDTO;
import com.dmall.pms.api.dto.product.response.get.SkuListResponseDTO;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.dataobject.SkuExtDO;
import com.dmall.pms.generator.mapper.SkuExtMapper;
import com.dmall.pms.generator.mapper.SkuMapper;
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
    private SkuExtMapper skuExtMapper;

    /**
     * 根据商品id查询列表
     */
    public List<SkuDO> selectByProductId(Long productId) {
        return skuMapper.selectList(Wrappers.<SkuDO>lambdaQuery()
                .eq(SkuDO::getProductId, productId));
    }

    /**
     * 新增商品的sku列表
     */
    public void saveSku(Long productId, Long brandId, List<AddSkuRequestDTO> skuList) {
        for (int i = 0; i < skuList.size(); i++) {
            AddSkuRequestDTO addSkuRequestDTO = skuList.get(i);
            SkuDO skuDO = new SkuDO();
            skuDO.setProductId(productId);
            skuDO.setBrandId(brandId);
            skuDO.setSkuNo(NoUtil.generateSkuNo());
            skuDO.setPrice(addSkuRequestDTO.getPrice());
            skuDO.setStock(addSkuRequestDTO.getStock());
            skuDO.setSort(i + 1);
            skuMapper.insert(skuDO);
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
                    skuListResponseDTO.setSkuNo(skuDO.getSkuNo());
                    skuListResponseDTO.setPrice(skuDO.getPrice());
                    skuListResponseDTO.setStock(skuDO.getStock());
                    SkuExtDO skuExtDO = skuExtMapper.selectOne(Wrappers.<SkuExtDO>lambdaQuery()
                            .eq(SkuExtDO::getSkuId, skuDO.getId()));
                    String skuSpecificationsJson = skuExtDO.getSkuSpecificationsJson();
                    JSONObject skuSpecifications = JSONObject.parseObject(skuSpecificationsJson);
                    skuListResponseDTO.setSpecifications(CollUtil.join(skuSpecifications.values(), ","));
                    return skuListResponseDTO;
                }).collect(Collectors.toList());
    }

}
