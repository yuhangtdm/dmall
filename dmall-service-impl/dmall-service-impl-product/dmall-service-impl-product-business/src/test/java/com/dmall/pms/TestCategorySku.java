package com.dmall.pms;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.generator.dataobject.CategorySkuDO;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.mapper.CategorySkuMapper;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.dmall.pms.service.support.CategorySkuSupport;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: created by hang.yu on 2020/1/4 16:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)
public class TestCategorySku {

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private CategorySkuSupport categorySkuSupport;

    @Autowired
    private CategorySkuMapper categorySkuMapper;

    @Test
    public void test() {
        List<SkuDO> skuDOS = skuMapper.selectList(Wrappers.emptyWrapper());
        for (SkuDO skuDO : skuDOS) {
            List<CategorySkuDO> list = Lists.newArrayList();
            List<CategorySkuDO> categorySkuDOS = categorySkuSupport.listBySkuId(skuDO.getId());
            for (CategorySkuDO categorySkuDO : categorySkuDOS) {
                boolean b = list.stream().anyMatch(categorySku -> {
                    return categorySku.getCategoryId().equals(categorySkuDO.getCategoryId());
                });
                if (list.size() == 0) {
                    b = false;
                }
                if (!b) {
                    list.add(categorySkuDO);
                }
            }

            List<Long> collect =
                categorySkuDOS.stream().filter(categorySkuDO -> !list.contains(categorySkuDO)).map(CategorySkuDO::getId)
                    .collect(Collectors.toList());
            if (CollUtil.isNotEmpty(collect)) {
                categorySkuMapper.deleteBatchIds(collect);

            }
        }
    }
}
