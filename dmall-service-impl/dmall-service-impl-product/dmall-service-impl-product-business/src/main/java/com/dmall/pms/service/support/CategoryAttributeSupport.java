package com.dmall.pms.service.support;

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
     * 根据商品分类id查询
     */
    public List<CategoryAttributeDO> listByCategoryId(Long categoryId) {
        return categoryAttributeMapper.selectList(Wrappers.<CategoryAttributeDO>lambdaQuery()
                .eq(CategoryAttributeDO::getCategoryId, categoryId));
    }

    /**
     * 根据属性id查询
     */
    public List<CategoryAttributeDO> listByAttributeId(Long attributeId) {
        return categoryAttributeMapper.selectList(Wrappers.<CategoryAttributeDO>lambdaQuery()
                .eq(CategoryAttributeDO::getAttributeId, attributeId));
    }

    public CategoryAttributeDO get(Long attributeId, Long categoryId) {
        return categoryAttributeMapper.selectOne(Wrappers.<CategoryAttributeDO>lambdaQuery()
                .eq(CategoryAttributeDO::getAttributeId, attributeId)
                .eq(CategoryAttributeDO::getCategoryId, categoryId));
    }

    /**
     * 根据商品分类id删除
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

    /**
     * 根据属性id和分类id列表删除
     */
    public void delete(Long attributeId, List<Long> categoryIds) {
        categoryAttributeMapper.delete(Wrappers.<CategoryAttributeDO>lambdaQuery()
                .eq(CategoryAttributeDO::getAttributeId, attributeId)
                .in(CategoryAttributeDO::getCategoryId, categoryIds));
    }

    /**
     * 根据分类id和属性id列表删除
     */
    public void delete(List<Long> attributeIds, Long categoryId) {
        categoryAttributeMapper.delete(Wrappers.<CategoryAttributeDO>lambdaQuery()
                .eq(CategoryAttributeDO::getCategoryId, categoryId)
                .in(CategoryAttributeDO::getAttributeId, attributeIds));
    }
}
