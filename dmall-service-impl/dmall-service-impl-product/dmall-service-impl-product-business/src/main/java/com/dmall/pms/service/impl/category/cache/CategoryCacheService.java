package com.dmall.pms.service.impl.category.cache;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.service.CommonCacheService;
import com.dmall.component.cache.redis.constants.CacheNameConstants;
import com.dmall.component.cache.redis.mapcache.*;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: CategoryCacheService
 * @author: created by hang.yu on 2019/11/25 22:51
 */
@Component
@MapCacheable(cacheNames = CacheNameConstants.CATEGORY)
public class CategoryCacheService implements CommonCacheService<CategoryDO> {

    @Autowired
    private CategoryMapper categoryMapper;

    @MapListCache(timeout = -1L)
    public List<CategoryDO> selectAll() {
        return categoryMapper.selectList(Wrappers.emptyWrapper());
    }

    @MapPostCache(timeout = -1L)
    public int insert(CategoryDO categoryDO) {
        return categoryMapper.insert(categoryDO);
    }

    @MapPutCache(timeout = -1L)
    public CategoryDO updateById(CategoryDO categoryDO) {
        categoryMapper.updateById(categoryDO);
        return categoryMapper.selectById(categoryDO.getId());
    }

    @MapDeleteCache
    public int deleteById(Long id) {
        return categoryMapper.deleteById(id);
    }

    @MapGetCache(timeout = -1L)
    public CategoryDO selectById(Long id) {
        return categoryMapper.selectById(id);
    }
}
