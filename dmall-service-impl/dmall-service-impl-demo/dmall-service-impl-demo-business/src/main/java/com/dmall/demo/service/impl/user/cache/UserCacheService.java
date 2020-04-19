package com.dmall.demo.service.impl.user.cache;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.CommonCacheService;
import com.dmall.component.cache.redis.CacheNameConstants;
import com.dmall.component.cache.redis.mapcache.*;
import com.dmall.demo.generator.dataobject.UserDO;
import com.dmall.demo.generator.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: User缓存操作
 * @author: created by hang.yu on 2019/12/3 22:18
 */
@Component
@MapCacheable(cacheNames = CacheNameConstants.USER)
public class UserCacheService implements CommonCacheService<UserDO> {

    @Autowired
    private UserMapper userMapper;

    @MapListCache
    public List<UserDO> selectAll() {
        return userMapper.selectList(Wrappers.emptyWrapper());
    }

    @MapPostCache
    public int insert(UserDO userDO) {
        return userMapper.insert(userDO);
    }

    @MapPutCache
    public UserDO updateById(UserDO userDO) {
        userMapper.updateById(userDO);
        return userMapper.selectById(userDO.getId());
    }

    @MapDeleteCache
    public int deleteById(Long id) {
        return userMapper.deleteById(id);
    }

    @MapGetCache
    public UserDO selectById(Long id) {
        return userMapper.selectById(id);
    }

}
