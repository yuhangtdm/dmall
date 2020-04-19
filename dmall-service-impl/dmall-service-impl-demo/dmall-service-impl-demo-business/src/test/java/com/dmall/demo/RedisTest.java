package com.dmall.demo;
import java.math.BigDecimal;
import java.util.Date;

import com.dmall.demo.generator.dataobject.UserDO;
import com.dmall.demo.service.impl.user.cache.UserCacheService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

/**
 * @description: RedisTest
 * @author: created by hang.yu on 2020/4/19 21:39
 */
public class RedisTest extends BaseTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserCacheService userCacheService;

    @Test
    public void testSet() {
        redisTemplate.opsForValue().set("hello", "redis");
    }

    @Test
    public void testSet2() {
        stringRedisTemplate.opsForValue().set("hello2", "redis");
    }

    @Test
    public void testGet() {
        System.out.println(redisTemplate.opsForValue().get("hello"));
    }

    @Test
    public void testSelectAll(){
        List<UserDO> userDOS = userCacheService.selectAll();
        System.out.println(userDOS);
    }

    @Test
    public void testGetObj(){
        UserDO userDO = userCacheService.selectById(1251871969155014662L);
        System.out.println(userDO);
    }

    @Test
    public void testInsert(){
        UserDO userDO = new UserDO();
        userDO.setMerchantsId(1L);
        userDO.setWarehouseId(1L);
        userDO.setUserName("系哈哈");
        userDO.setNickName("系哈哈");
        userDO.setPhone("1562231545");
        userDO.setEmail("14515614@qq.com");
        userDO.setPassword("123456");
        userDO.setRealName("王五");
        userDO.setIcon("yy");
        userDO.setRemark("yy");
        userDO.setMoney(BigDecimal.ONE);
        userCacheService.insert(userDO);
    }

    @Test
    public void testUpdate(){
        UserDO userDO = userCacheService.selectById(1251871969155014662L);
        userDO.setNickName("烽烟大漠");
        userCacheService.updateById(userDO);
    }

    @Test
    public void testDelete(){
        userCacheService.deleteById(1251871969155014662L);
    }
}
