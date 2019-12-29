package com.dmall.pms.service.impl.attribute.cache;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.service.CommonCacheService;
import com.dmall.component.cache.redis.constants.CacheNameConstants;
import com.dmall.component.cache.redis.mapcache.*;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.mapper.AttributeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: AttributeCacheService
 * @author: created by hang.yu on 2019/12/7 22:26
 */
@Component
@MapCacheable(cacheNames = CacheNameConstants.ATTRIBUTE)
public class AttributeCacheService implements CommonCacheService<AttributeDO> {

    @Autowired
    private AttributeMapper attributeMapper;

    @MapListCache(timeout = -1L)
    public List<AttributeDO> selectAll() {
        return attributeMapper.selectList(Wrappers.emptyWrapper());
    }

    @MapPostCache(timeout = -1L)
    public int insert(AttributeDO attributeDO) {
        return attributeMapper.insert(attributeDO);
    }

    @MapPutCache(timeout = -1L)
    public AttributeDO updateById(AttributeDO attributeDO) {
        attributeMapper.updateById(attributeDO);
        return attributeMapper.selectById(attributeDO.getId());
    }

    @MapDeleteCache
    public int deleteById(Long id) {
        return attributeMapper.deleteById(id);
    }

    @MapGetCache(timeout = -1L)
    public AttributeDO selectById(Long id) {
        return attributeMapper.selectById(id);
    }


}
