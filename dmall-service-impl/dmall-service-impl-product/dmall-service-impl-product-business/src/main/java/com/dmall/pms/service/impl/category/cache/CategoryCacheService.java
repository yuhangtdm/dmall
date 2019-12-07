package com.dmall.pms.service.impl.category.cache;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.cache.redis.mapcache.MapCacheable;
import com.dmall.component.cache.redis.mapcache.MapDeleteCache;
import com.dmall.component.cache.redis.mapcache.MapGetCache;
import com.dmall.component.cache.redis.mapcache.MapPutCache;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: created by hang.yu on 2019/11/25 22:51
 */
@Component
public class CategoryCacheService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询列表
     */
    @MapCacheable(cacheNames = "category")
    public List<CategoryDO> selectAll() {
        return categoryMapper.selectList(Wrappers.emptyWrapper());
    }

    /**
     * 新增
     */
    @MapPutCache(cacheNames = "category")
    public int insert(CategoryDO brandDO) {
        return categoryMapper.insert(brandDO);
    }

    /**
     * 修改
     */
    @MapPutCache(cacheNames = "category")
    public int updateById(CategoryDO brandDO) {
        return categoryMapper.updateById(brandDO);
    }

    /**
     * 删除
     */
    @MapDeleteCache(cacheNames = "category")
    public int deleteById(Long id) {
        return categoryMapper.deleteById(id);
    }

    /**
     * 查询对象
     */
    @MapGetCache(cacheNames = "category")
    public CategoryDO selectById(Long id) {
        return categoryMapper.selectById(id);
    }
}
