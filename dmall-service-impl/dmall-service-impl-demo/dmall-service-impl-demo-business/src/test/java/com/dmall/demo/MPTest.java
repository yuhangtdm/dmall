package com.dmall.demo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.demo.generator.dataobject.UserDO;
import com.dmall.demo.generator.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: MPTest
 * @author: created by hang.yu on 2020/5/7 20:52
 */
public class MPTest extends BaseTest{

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        IPage<UserDO> page = new Page<>(1,2);
        userMapper.selectPage(page, Wrappers.emptyWrapper());
        System.out.println(page.getRecords());
        System.out.println(page.getTotal());
    }
}
