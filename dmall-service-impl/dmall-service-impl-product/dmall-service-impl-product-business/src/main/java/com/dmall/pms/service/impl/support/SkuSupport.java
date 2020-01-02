package com.dmall.pms.service.impl.support;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.enums.base.YNEnum;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.util.NoUtil;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.product.request.attributevalue.AddSkuRequestDTO;
import com.dmall.pms.api.dto.product.response.get.SkuListResponseDTO;
import com.dmall.pms.api.dto.sku.enums.SkuAuditStatusEnum;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.dataobject.CategorySkuDO;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.dataobject.SkuExtDO;
import com.dmall.pms.generator.mapper.CategorySkuMapper;
import com.dmall.pms.generator.mapper.SkuExtMapper;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.impl.sku.enums.SkuErrorEnum;
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
    private SkuExtSupport skuExtSupport;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private CategorySkuMapper categorySkuMapper;

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
    public void saveSku(Long productId, List<Long> categoryIds, Long brandId, List<AddSkuRequestDTO> skuList) {
        for (int i = 0; i < skuList.size(); i++) {
            AddSkuRequestDTO addSkuRequestDTO = skuList.get(i);
            SkuDO skuDO = new SkuDO();
            skuDO.setProductId(productId);
            skuDO.setBrandId(brandId);
            skuDO.setSkuNo(NoUtil.generateSkuNo());
            skuDO.setPrice(addSkuRequestDTO.getPrice());
            skuDO.setStock(addSkuRequestDTO.getStock());
            skuDO.setAuditStatus(SkuAuditStatusEnum.NOT_AUDIT.getCode());
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
                    SkuExtDO skuExtDO = skuExtSupport.getBySkuId(skuDO.getId());
                    if (skuExtDO != null){
                        String skuSpecificationsJson = skuExtDO.getSkuSpecificationsJson();
                        JSONObject skuSpecifications = JSONObject.parseObject(skuSpecificationsJson);
                        skuListResponseDTO.setSpecifications(CollUtil.join(skuSpecifications.values(), ","));
                    }
                    return skuListResponseDTO;
                }).collect(Collectors.toList());
    }

    /**
     * 公共校验逻辑
     */
    public BaseResult validate(Long skuId) {
        SkuDO skuDO = skuMapper.selectById(skuId);
        if (skuDO == null) {
            return ResultUtil.fail(SkuErrorEnum.SKU_NOT_EXIST);
        }
        return ResultUtil.success();
    }
}
