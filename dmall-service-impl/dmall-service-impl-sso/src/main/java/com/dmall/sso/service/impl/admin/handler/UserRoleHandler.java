package com.dmall.sso.service.impl.admin.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.sso.api.dto.admin.RoleResponseDTO;
import com.dmall.sso.service.impl.admin.dataobject.RoleDO;
import com.dmall.sso.service.impl.admin.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: UserRoleHandler
 * @author: created by hang.yu on 2020/1/12 10:35
 */
@Component
public class UserRoleHandler extends AbstractCommonHandler<String, RoleDO, RoleResponseDTO> {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public BaseResult processor(String userName) {
        List<RoleDO> roleDOS = userRoleMapper.listByUserName(userName);
        List<RoleResponseDTO> result = roleDOS.stream().map(doo -> doConvertDto(doo, RoleResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(result);
    }
}
