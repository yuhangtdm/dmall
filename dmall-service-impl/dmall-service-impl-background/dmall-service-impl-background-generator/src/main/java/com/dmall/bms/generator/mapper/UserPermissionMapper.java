package com.dmall.bms.generator.mapper;

import com.dmall.bms.generator.dataobject.UserPermissionDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @description: 后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限
 * @author: created by hang.yu on 2020-01-11 19:13:17
 */
public interface UserPermissionMapper extends BaseMapper<UserPermissionDO> {

}
