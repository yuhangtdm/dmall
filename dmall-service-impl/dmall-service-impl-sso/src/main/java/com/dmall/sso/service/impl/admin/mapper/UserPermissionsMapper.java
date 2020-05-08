package com.dmall.sso.service.impl.admin.mapper;

import com.dmall.sso.api.dto.admin.PermissionResponseDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: UserPermissionMapper
 * @author: created by hang.yu on 2020/1/11 19:03
 */
public interface UserPermissionsMapper {

    List<PermissionResponseDTO > listByPhone(@Param("phone") String phone);

    List<Long> subPermissionsByPhone(@Param("phone") String phone);
}
