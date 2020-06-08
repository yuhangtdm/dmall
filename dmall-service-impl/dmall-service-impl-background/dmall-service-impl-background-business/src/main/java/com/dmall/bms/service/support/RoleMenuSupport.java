package com.dmall.bms.service.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.bms.generator.dataobject.RoleMenuDO;
import com.dmall.bms.generator.mapper.RoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: RoleMenuSupport
 * @author: created by hang.yu on 2020/5/16 10:41
 */
@Component
public class RoleMenuSupport {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    /**
     * 根据角色id查询列表
     */
    public List<RoleMenuDO> listByRoleId(Long roleId) {
        return roleMenuMapper.selectList(Wrappers.<RoleMenuDO>lambdaQuery().eq(RoleMenuDO::getRoleId, roleId));
    }

    /**
     * 根据角色id和菜单id删除
     */
    public void delete(Long roleId, List<Long> deleteMenuIds) {
        roleMenuMapper.delete(Wrappers.<RoleMenuDO>lambdaQuery()
            .eq(RoleMenuDO::getRoleId, roleId)
            .in(RoleMenuDO::getMenuId, deleteMenuIds));
    }
}
