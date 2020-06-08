package com.dmall.bms.service.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.bms.generator.dataobject.UserRoleDO;
import com.dmall.bms.generator.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: UserRoleSupport
 * @author: created by hang.yu on 2020/2/20 20:58
 */
@Component
public class UserRoleSupport {

    @Autowired
    private UserRoleMapper userRoleMapper;

    public void deleteByUserId(Long userId) {
        userRoleMapper.delete(Wrappers.<UserRoleDO>lambdaQuery().eq(UserRoleDO::getUserId, userId));
    }

    /**
     * 根据用户id获取角色列表
     */
    public List<UserRoleDO> listByUserId(Long userId) {
        return userRoleMapper.selectList(Wrappers.<UserRoleDO>lambdaQuery()
            .eq(UserRoleDO::getUserId, userId));
    }
}
