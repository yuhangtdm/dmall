package com.dmall.pms.service.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.generator.dataobject.CategoryBrandDO;
import com.dmall.pms.generator.mapper.CategoryBrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: CategoryBrandSupport
 * @author: created by hang.yu on 2019/12/29 17:48
 */
@Component
public class CategoryBrandSupport {

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    /**
     * 根据商品分类id查询
     */
    public List<CategoryBrandDO> listByCategoryId(Long categoryId) {
        return categoryBrandMapper.selectList(Wrappers.<CategoryBrandDO>lambdaQuery()
                .eq(CategoryBrandDO::getCategoryId, categoryId));
    }

    /**
     * 根据品牌id查询
     */
    public List<CategoryBrandDO> listByBrandId(Long brandId) {
        return categoryBrandMapper.selectList(Wrappers.<CategoryBrandDO>lambdaQuery()
                .eq(CategoryBrandDO::getBrandId, brandId));
    }

    /**
     * 根据商品分类id删除
     */
    public void deleteByCategoryId(Long categoryId) {
        categoryBrandMapper.delete(Wrappers.<CategoryBrandDO>lambdaQuery().eq(CategoryBrandDO::getCategoryId, categoryId));
    }

    /**
     * 根据品牌id删除
     */
    public void deleteByBrandId(Long brandId) {
        categoryBrandMapper.delete(Wrappers.<CategoryBrandDO>lambdaQuery().eq(CategoryBrandDO::getBrandId, brandId));
    }

    public void delete(Long brandId, List<Long> categoryIds) {
        categoryBrandMapper.delete(Wrappers.<CategoryBrandDO>lambdaQuery()
                .eq(CategoryBrandDO::getBrandId, brandId)
                .in(CategoryBrandDO::getCategoryId, categoryIds)
        );
    }
}
