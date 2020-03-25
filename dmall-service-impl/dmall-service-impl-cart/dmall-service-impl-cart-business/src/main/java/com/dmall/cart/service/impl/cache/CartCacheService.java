package com.dmall.cart.service.impl.cache;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.cart.generator.dataobject.CartItemDO;
import com.dmall.cart.generator.mapper.CartItemMapper;
import com.dmall.component.cache.redis.CacheNameConstants;
import com.dmall.component.cache.redis.DMallRedisProperties;
import com.dmall.component.cache.redis.mapcache.MapCacheUtil;
import com.dmall.component.cache.redis.mapcache.MapCacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 购物车缓存类
 * @author: created by hang.yu on 2020/3/14 15:16
 */
@Component
@MapCacheable(cacheNames = CacheNameConstants.CATEGORY)
public class CartCacheService {

    @Autowired
    private MapCacheUtil mapCacheUtil;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private RedisTemplate<String, Object> dMallRedisTemplate;

    @Autowired
    private DMallRedisProperties dMallRedisProperties;

    /**
     * 获取购物车的key
     */
    public String getCartKey(Long skuId) {
        return StrUtil.format("{}:memberId_{}", dMallRedisProperties.getCacheKeyPrefix(), skuId);
    }

    /**
     * 插入缓存
     */
    public void insert(Long memberId, CartItemDO cartItemDO) {
        // 存入redis 没有过期时间
        mapCacheUtil.put(this.getCartKey(memberId), String.valueOf(cartItemDO.getSkuId()), cartItemDO);
    }

    /**
     * 删除缓存
     */
    public void delete(Long memberId, List<Long> skuIds) {
        List<String> collect = skuIds.stream().map(String::valueOf).collect(Collectors.toList());
        String[] arr = new String[collect.size()];
        mapCacheUtil.delete(this.getCartKey(memberId), collect.toArray(arr));
    }

    /**
     * 查询缓存列表
     */
    public List<CartItemDO> list(Long memberId) {
        HashOperations<String, String, CartItemDO> ops = dMallRedisTemplate.opsForHash();
        List<CartItemDO> values = ops.values(this.getCartKey(memberId));
        if (CollUtil.isEmpty(values)) {
            return cartItemMapper.selectList(Wrappers.<CartItemDO>lambdaQuery()
                    .eq(CartItemDO::getMemberId, memberId));
        }
        return values;
    }
}
