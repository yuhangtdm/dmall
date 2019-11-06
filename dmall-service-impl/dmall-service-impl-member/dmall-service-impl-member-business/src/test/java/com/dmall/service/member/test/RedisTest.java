package com.dmall.service.member.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @description:
 * @author: created by yuhang on 2019/11/4 23:33
 */
public class RedisTest extends BaseTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testInsert(){
        stringRedisTemplate.opsForValue().set("msg","helloSpringboot+redis");
    }
}
