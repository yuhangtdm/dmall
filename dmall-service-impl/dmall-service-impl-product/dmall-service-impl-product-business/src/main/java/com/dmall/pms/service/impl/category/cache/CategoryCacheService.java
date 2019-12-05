package com.dmall.pms.service.impl.category.cache;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.cache.redis.mapcache.MapCacheable;
import com.dmall.component.cache.redis.mapcache.MapDeleteCache;
import com.dmall.component.cache.redis.mapcache.MapGetCache;
import com.dmall.component.cache.redis.mapcache.MapPutCache;
import com.dmall.pms.api.dto.category.request.ListCategoryRequestDTO;
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
    public List<CategoryDO> selectList(ListCategoryRequestDTO requestDTO) {
        LambdaQueryWrapper<CategoryDO> queryWrapper = Wrappers.<CategoryDO>lambdaQuery()
                .like(StrUtil.isNotBlank(requestDTO.getName()), CategoryDO::getName, requestDTO.getName())
                .eq(ObjectUtil.isNotNull(requestDTO.getParentId()), CategoryDO::getParentId, requestDTO.getParentId())
                .eq(ObjectUtil.isNotNull(requestDTO.getLevel()), CategoryDO::getLevel,
                        requestDTO.getLevel() != null ? requestDTO.getLevel() : 0)
                .orderByAsc(CategoryDO::getSort);
        return categoryMapper.selectList(queryWrapper);
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
