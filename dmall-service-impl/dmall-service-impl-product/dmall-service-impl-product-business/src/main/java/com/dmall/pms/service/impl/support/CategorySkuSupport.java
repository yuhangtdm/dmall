package com.dmall.pms.service.impl.support;

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

    public List<CategorySkuDO> getCategorySkus(Long skuId) {
        return categorySkuMapper.selectList(Wrappers.<CategorySkuDO>lambdaQuery().eq(CategorySkuDO::getSkuId, skuId));
    }

    public List<Long> getCategoryIds(Long skuId) {
        return getCategorySkus(skuId).stream().map(CategorySkuDO::getCategoryId).collect(Collectors.toList());
    }
}
