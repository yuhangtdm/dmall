package com.dmall.bms.service.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.bms.generator.dataobject.RoleDO;
import com.dmall.bms.generator.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: RoleSupport
 * @author: created by hang.yu on 2020/1/15 22:34
 */
@Component
public class RoleSupport {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据角色名查询角色信息
     */
    public RoleDO getByName(String name) {
        return roleMapper.selectOne(Wrappers.<RoleDO>lambdaQuery()
                .eq(RoleDO::getName, name));
    }

}
