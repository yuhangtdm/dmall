package com.dmall.sso.service.impl.admin.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.sso.api.dto.admin.PermissionResponseDTO;
import com.dmall.sso.service.impl.admin.dataobject.PermissionDO;
import com.dmall.sso.service.impl.admin.mapper.UserPermissionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: UserPermissionHandler
 * @author: created by hang.yu on 2020/1/12 10:38
 */
@Component
public class UserPermissionHandler extends AbstractCommonHandler<String, PermissionDO, PermissionResponseDTO> {

    @Autowired
    private UserPermissionsMapper userPermissionsMapper;

    @Override
    public BaseResult<List<PermissionResponseDTO>> processor(String phone) {
        // 用户的权限以及额外的加权限
        List<PermissionResponseDTO> permissionDOS = userPermissionsMapper.listByPhone(phone);

        // 用户的减权限
        List<Long> ids = userPermissionsMapper.subPermissionsByPhone(phone);

        // 返回的数据
        List<PermissionResponseDTO> result = permissionDOS.stream()
            .filter(permissionResponse -> !ids.contains(permissionResponse.getId()))
            .collect(Collectors.toList());
        return ResultUtil.success(result);
    }
}
