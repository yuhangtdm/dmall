package com.dmall.pms.service.impl.brand.cache;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.service.CommonCacheService;
import com.dmall.component.cache.redis.constants.CacheNameConstants;
import com.dmall.component.cache.redis.mapcache.*;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: Brand缓存操作
 * @author: created by hang.yu on 2019/12/3 22:18
 */
@Component
@MapCacheable(cacheNames = CacheNameConstants.BRAND)
public class BrandCacheService implements CommonCacheService<BrandDO> {

    @Autowired
    private BrandMapper brandMapper;

    @MapListCache(timeout = -1L)
    public List<BrandDO> selectAll() {
        return brandMapper.selectList(Wrappers.emptyWrapper());
    }

    @MapPostCache(timeout = -1L)
    public int insert(BrandDO brandDO) {
        return brandMapper.insert(brandDO);
    }

    @MapPutCache(timeout = -1L)
    public BrandDO updateById(BrandDO brandDO) {
        brandMapper.updateById(brandDO);
        return brandMapper.selectById(brandDO.getId());
    }

    @MapDeleteCache
    public int deleteById(Long id) {
        return brandMapper.deleteById(id);
    }

    @MapGetCache(timeout = -1L)
    public BrandDO selectById(Long id) {
        return brandMapper.selectById(id);
    }

}
