package com.dmall.pms.service.impl.support;

import cn.hutool.core.util.StrUtil;
import com.dmall.pms.api.dto.sku.request.LockSkuRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: sku库存服务
 * @author: created by hang.yu on 2020/3/29 11:09
 */
@Component
public class SkuStockSupport {

    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    private static final String SKU_STOCK_KEY = "stock:sku_{}";

    /**
     * 获取当前可售库存
     */
    public Integer getSaleableStock(Long skuId) {
        return redisTemplate.opsForValue().get(StrUtil.format(SKU_STOCK_KEY, skuId));
    }

    /**
     * 锁定库存: 利用redis的单线程保证线程安全
     */
    public void lockStock(List<LockSkuRequestDTO> sku) {
        for (LockSkuRequestDTO skuRequest : sku) {
            redisTemplate.opsForValue()
                    .decrement(StrUtil.format(SKU_STOCK_KEY, skuRequest.getSkuId()), skuRequest.getNumber());
        }
    }
}
