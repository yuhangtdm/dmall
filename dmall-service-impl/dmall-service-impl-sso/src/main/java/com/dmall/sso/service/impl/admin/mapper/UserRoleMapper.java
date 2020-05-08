package com.dmall.sso.service.impl.admin.mapper;

import com.dmall.sso.service.impl.admin.dataobject.RoleDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 后台用户-角色表
 * @author: created by hang.yu on 2020-01-05 18:36:38
 */
public interface UserRoleMapper {

    List<RoleDO> listByPhone(@Param("phone") String phone);
}
