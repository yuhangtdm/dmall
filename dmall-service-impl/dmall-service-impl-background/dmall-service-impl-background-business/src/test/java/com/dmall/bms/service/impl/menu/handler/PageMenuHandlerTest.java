package com.dmall.bms.service.impl.menu.handler;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.bms.generator.dataobject.MenuDO;
import com.dmall.bms.generator.dataobject.UserDO;
import com.dmall.bms.generator.mapper.MenuMapper;
import com.dmall.bms.generator.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: created by hang.yu on 2020/5/7 20:59
 */
public class PageMenuHandlerTest extends BaseTest {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void processor() {
        IPage<MenuDO> page = new Page<>(1, 2);
        menuMapper.selectPage(page, Wrappers.emptyWrapper());
        System.out.println(page.getRecords());
        System.out.println(page.getTotal());
    }

    @Test
    public void test(){
        IPage<UserDO> page = new Page<>(1,2);
        userMapper.selectPage(page, Wrappers.emptyWrapper());
        System.out.println(page.getRecords());
        System.out.println(page.getTotal());
    }
}