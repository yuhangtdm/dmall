package com.dmall.sso.service.impl.admin.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.sso.api.dto.PermissionResponseDTO;
import com.dmall.sso.service.impl.admin.dataobject.PermissionDO;
import com.dmall.sso.service.impl.admin.mapper.UserPermissionMapper;
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
    private UserPermissionMapper userPermissionMapper;

    @Override
    public BaseResult processor(String userName) {
        List<PermissionDO> permissionDOS = userPermissionMapper.listByUserName(userName);
        List<PermissionResponseDTO> result = permissionDOS.stream().map(doo -> doConvertDto(doo, PermissionResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(result);
    }
}
