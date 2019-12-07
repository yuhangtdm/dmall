package com.dmall.pms.service.impl.attribute.cache;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.cache.redis.mapcache.MapCacheable;
import com.dmall.component.cache.redis.mapcache.MapDeleteCache;
import com.dmall.component.cache.redis.mapcache.MapGetCache;
import com.dmall.component.cache.redis.mapcache.MapPutCache;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.mapper.AttributeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/7 22:26
 */
@Component
public class AttributeCacheService {

    @Autowired
    private AttributeMapper attributeMapper;

    @MapCacheable(cacheNames = "attribute")
    public List<AttributeDO> selectAll() {
        return attributeMapper.selectList(Wrappers.emptyWrapper());
    }

    /**
     * 新增
     */
    @MapPutCache(cacheNames = "attribute")
    public int insert(AttributeDO attributeDO) {
        return attributeMapper.insert(attributeDO);
    }

    /**
     * 修改
     */
    @MapPutCache(cacheNames = "attribute")
    public int updateById(AttributeDO attributeDO) {
        return attributeMapper.updateById(attributeDO);
    }

    /**
     * 删除
     */
    @MapDeleteCache(cacheNames = "attribute")
    public int deleteById(Long id) {
        return attributeMapper.deleteById(id);
    }

    /**
     * 查询对象
     */
    @MapGetCache(cacheNames = "attribute")
    public AttributeDO selectById(Long id) {
        return attributeMapper.selectById(id);
    }
}
