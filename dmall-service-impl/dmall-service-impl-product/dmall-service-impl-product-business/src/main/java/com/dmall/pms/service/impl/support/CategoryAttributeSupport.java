package com.dmall.pms.service.impl.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.generator.dataobject.CategoryAttributeDO;
import com.dmall.pms.generator.mapper.CategoryAttributeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: CategoryAttributeSupport
 * @author: created by hang.yu on 2019/12/29 17:51
 */
@Component
public class CategoryAttributeSupport {
    @Autowired
    private CategoryAttributeMapper categoryAttributeMapper;

    /**
     * 根据分类id查询
     */
    public List<CategoryAttributeDO> listByCategoryId(Long categoryId) {
        return categoryAttributeMapper.selectList(Wrappers.<CategoryAttributeDO>lambdaQuery()
                .eq(CategoryAttributeDO::getCategoryId, categoryId));
    }

    /**
     * 根据分类id删除
     */
    public void deleteByCategoryId(Long categoryId) {
        categoryAttributeMapper.delete(Wrappers.<CategoryAttributeDO>lambdaQuery()
                .eq(CategoryAttributeDO::getCategoryId, categoryId));
    }

    /**
     * 根据属性id删除
     */
    public void deleteByAttributeId(Long attributeId) {
        categoryAttributeMapper.delete(Wrappers.<CategoryAttributeDO>lambdaQuery()
                .eq(CategoryAttributeDO::getAttributeId, attributeId));
    }
}
