package com.dmall.pms.service.impl.attributetype.cache;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.CommonCacheService;
import com.dmall.component.cache.redis.constants.CacheNameConstants;
import com.dmall.component.cache.redis.mapcache.*;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: AttributeTypeCacheService
 * @author: created by hang.yu on 2019/12/7 21:21
 */
@Component
@MapCacheable(cacheNames = CacheNameConstants.ATTRIBUTE_TYPE)
public class AttributeTypeCacheService implements CommonCacheService<AttributeTypeDO> {

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @MapListCache
    public List<AttributeTypeDO> selectAll() {
        return attributeTypeMapper.selectList(Wrappers.emptyWrapper());
    }

    @MapPostCache
    public int insert(AttributeTypeDO attributeTypeDO) {
        return attributeTypeMapper.insert(attributeTypeDO);
    }

    @MapPutCache
    public AttributeTypeDO updateById(AttributeTypeDO attributeTypeDO) {
        attributeTypeMapper.updateById(attributeTypeDO);
        return attributeTypeMapper.selectById(attributeTypeDO.getId());
    }

    @MapDeleteCache
    public int deleteById(Long id) {
        return attributeTypeMapper.deleteById(id);
    }

    @MapGetCache
    public AttributeTypeDO selectById(Long id) {
        return attributeTypeMapper.selectById(id);
    }
}
