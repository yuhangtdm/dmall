package com.dmall.sso.service.impl.admin.mapper;

import com.dmall.sso.service.impl.admin.dataobject.PermissionDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: UserPermissionMapper
 * @author: created by hang.yu on 2020/1/11 19:03
 */
public interface UserPermissionMapper {

    List<PermissionDO> listByUserName(@Param("userName") String userName);

}
