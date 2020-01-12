package com.dmall.bms.generator.service.impl;

import com.dmall.bms.generator.dataobject.UserPermissionDO;
import com.dmall.bms.generator.mapper.UserPermissionMapper;
import com.dmall.bms.generator.service.IUserPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: 后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限
 * @author: created by hang.yu on 2020-01-11 19:13:17
 */
@Service
public class IUserPermissionServiceImpl extends ServiceImpl<UserPermissionMapper, UserPermissionDO> implements IUserPermissionService {

}
