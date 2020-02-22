package com.dmall.bms.service.impl.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.bms.generator.dataobject.RolePermissionDO;
import com.dmall.bms.generator.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @description: RolePermissionSupport
 * @author: created by hang.yu on 2020/2/20 22:01
 */
@Component
public class RolePermissionSupport {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 根据角色id查询列表
     */
    public List<RolePermissionDO> listByRoleId(Long roleId) {
        return rolePermissionMapper.selectList(Wrappers.<RolePermissionDO>lambdaQuery().eq(RolePermissionDO::getRoleId, roleId));
    }

    /**
     * 根据角色id,权限id集合删除
     */
    public void delete(Long roleId, Collection<Long> permissionIds) {
        rolePermissionMapper.delete(Wrappers.<RolePermissionDO>lambdaQuery()
                .eq(RolePermissionDO::getRoleId, roleId)
                .in(RolePermissionDO::getPermissionId, permissionIds));
    }
}
