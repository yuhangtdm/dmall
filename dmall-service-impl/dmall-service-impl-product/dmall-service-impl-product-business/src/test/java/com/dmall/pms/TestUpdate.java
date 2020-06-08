package com.dmall.pms;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.api.enums.LevelEnum;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.AttributeMapper;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import com.dmall.pms.generator.mapper.CategoryMapper;
import com.dmall.pms.generator.service.ICategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/7 22:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)
public class TestUpdate {
    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private AttributeMapper attributeMapper;

    @Test
    public void test() {
        AttributeTypeDO attributeTypeDO = new AttributeTypeDO();
        attributeTypeDO.setId(1L);
        attributeTypeDO.setCategoryId(2L);
        LambdaUpdateWrapper<AttributeTypeDO> updateWrapper = Wrappers.<AttributeTypeDO>update().lambda()
            .eq(AttributeTypeDO::getId, 1L)
            .set(AttributeTypeDO::getCategoryId, null)
            .set(AttributeTypeDO::getCascadeCategoryId, null);
        // attributeTypeMapper.update(null, updateWrapper);

        attributeTypeMapper.updateById(attributeTypeDO);
    }

    @Test
    public void test2() {
        List<CategoryDO> categoryDOList = categoryMapper.selectList(Wrappers.emptyWrapper());
        for (CategoryDO categoryDO : categoryDOList) {
            categoryDO.setName(categoryDO.getName().trim());
            categoryMapper.updateById(categoryDO);
        }
    }

    @Test
    public void test3() {
        List<AttributeDO> attributeList = attributeMapper.selectList(Wrappers.emptyWrapper());
        for (AttributeDO attributeDO : attributeList) {
            String[] s = attributeDO.getName().split("_");
            CategoryDO categoryDO = categoryMapper
                .selectOne(Wrappers.<CategoryDO>lambdaQuery().eq(CategoryDO::getLevel, LevelEnum.ONE.getCode())
                    .eq(CategoryDO::getName, s[0]));
            attributeDO.setCategoryId(categoryDO.getId());
            attributeMapper.updateById(attributeDO);
        }

    }

}
