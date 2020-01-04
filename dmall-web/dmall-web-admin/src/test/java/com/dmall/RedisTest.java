package com.dmall;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

/**
 * @description:
 * @author: created by hang.yu on 2019/11/4 23:33
 */
public class RedisTest extends BaseTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
//    @Qualifier("dMallRedisTemplate")
    private RedisTemplate dMallRedisTemplate;

    @Test
    public void testInsert() {
        stringRedisTemplate.opsForValue().set("msg", "helloSpringboot+redis", Duration.ofSeconds(50));
    }

    @Test
    public void testMap() {
        dMallRedisTemplate.opsForHash().put("phone", "iphone", "苹果");
    }

    @Test
    public void testGetMap() {
        Object cache = dMallRedisTemplate.opsForHash().get("phone", "iphone");
        System.err.println(cache);
    }

}
