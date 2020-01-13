package com.dmall.bms.generator.service.impl;

import com.dmall.bms.generator.dataobject.UserRoleDO;
import com.dmall.bms.generator.mapper.UserRoleMapper;
import com.dmall.bms.generator.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: 后台用户-角色表
 * @author: created by hang.yu on 2020-01-13 23:04:04
 */
@Service
public class IUserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleDO> implements IUserRoleService {

}
