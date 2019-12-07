package com.dmall.pms.service.impl.attributetype.cache;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.cache.redis.mapcache.MapCacheable;
import com.dmall.component.cache.redis.mapcache.MapDeleteCache;
import com.dmall.component.cache.redis.mapcache.MapGetCache;
import com.dmall.component.cache.redis.mapcache.MapPutCache;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/7 21:21
 */
@Component
public class AttributeTypeCacheService {

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @MapCacheable(cacheNames = "attributeType")
    public List<AttributeTypeDO> selectAll() {
        return attributeTypeMapper.selectList(Wrappers.emptyWrapper());
    }

    @MapPutCache(cacheNames = "attributeType")
    public int insert(AttributeTypeDO attributeTypeDO){
        return attributeTypeMapper.insert(attributeTypeDO);
    }

    @MapPutCache(cacheNames = "attributeType")
    public int updateById(AttributeTypeDO brandDO) {
        return attributeTypeMapper.updateById(brandDO);
    }

    /**
     * 删除
     */
    @MapDeleteCache(cacheNames = "attributeType")
    public int deleteById(Long id) {
        return attributeTypeMapper.deleteById(id);
    }

    /**
     * 查询对象
     */
    @MapGetCache(cacheNames = "attributeType")
    public AttributeTypeDO selectById(Long id) {
        return attributeTypeMapper.selectById(id);
    }
}
