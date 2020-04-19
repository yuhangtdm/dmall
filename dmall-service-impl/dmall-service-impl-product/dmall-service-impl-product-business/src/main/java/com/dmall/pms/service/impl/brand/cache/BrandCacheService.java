package com.dmall.pms.service.impl.brand.cache;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.CommonCacheService;
import com.dmall.component.cache.redis.CacheNameConstants;
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

    @MapListCache
    public List<BrandDO> selectAll() {
        return brandMapper.selectList(Wrappers.emptyWrapper());
    }

    @MapPutCache
    public int insert(BrandDO brandDO) {
        return brandMapper.insert(brandDO);
    }

    @MapPutCache
    public BrandDO updateById(BrandDO brandDO) {
        brandMapper.updateById(brandDO);
        return brandMapper.selectById(brandDO.getId());
    }

    @MapDeleteCache
    public int deleteById(Long id) {
        return brandMapper.deleteById(id);
    }

    @MapGetCache
    public BrandDO selectById(Long id) {
        return brandMapper.selectById(id);
    }

}
