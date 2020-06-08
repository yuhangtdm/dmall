package com.dmall.pms.service.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.generator.dataobject.CategorySkuDO;
import com.dmall.pms.generator.mapper.CategorySkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: CategorySkuSupport
 * @author: created by hang.yu on 2019/12/29 16:49
 */
@Component
public class CategorySkuSupport {

    @Autowired
    private CategorySkuMapper categorySkuMapper;

    /**
     * 根据skuId查询列表
     */
    public List<CategorySkuDO> listBySkuId(Long skuId) {
        return categorySkuMapper.selectList(Wrappers.<CategorySkuDO>lambdaQuery().eq(CategorySkuDO::getSkuId, skuId));
    }

    /**
     * 根据skuId查询商品分类id列表
     */
    public List<Long> getCategoryIds(Long skuId) {
        return listBySkuId(skuId).stream().map(CategorySkuDO::getCategoryId).collect(Collectors.toList());
    }

    /**
     * 根据商品分类id查询列表
     */
    public List<CategorySkuDO> listByCategoryId(Long categoryId) {
        return categorySkuMapper
            .selectList(Wrappers.<CategorySkuDO>lambdaQuery().eq(CategorySkuDO::getCategoryId, categoryId));
    }

    /**
     * 根据skuId删除
     */
    public void deleteBySkuId(Long skuId) {
        categorySkuMapper.delete(Wrappers.<CategorySkuDO>lambdaQuery().eq(CategorySkuDO::getSkuId, skuId));
    }
}
