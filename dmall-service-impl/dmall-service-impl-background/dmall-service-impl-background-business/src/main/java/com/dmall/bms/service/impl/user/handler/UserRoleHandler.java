package com.dmall.bms.service.impl.user.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.bms.api.dto.user.response.UserRoleResponseDTO;
import com.dmall.bms.generator.dataobject.RoleDO;
import com.dmall.bms.generator.dataobject.UserRoleDO;
import com.dmall.bms.generator.mapper.RoleMapper;
import com.dmall.bms.service.support.UserRoleSupport;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: UserRoleHandler
 * @author: created by hang.yu on 2020/5/16 20:58
 */
@Component
public class UserRoleHandler extends AbstractCommonHandler<Long, RoleDO, UserRoleResponseDTO> {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleSupport userRoleSupport;

    @Override
    public BaseResult<List<UserRoleResponseDTO>> processor(Long userId) {
        List<RoleDO> roleList = roleMapper.selectList(Wrappers.emptyWrapper());
        List<Long> roleIds = userRoleSupport.listByUserId(userId).stream().map(UserRoleDO::getRoleId).collect(Collectors.toList());
        List<UserRoleResponseDTO> result = roleList.stream().map(roleDO -> {
            UserRoleResponseDTO userRoleResponseDTO = new UserRoleResponseDTO();
            userRoleResponseDTO.setRoleId(roleDO.getId());
            userRoleResponseDTO.setRoleName(roleDO.getName());
            userRoleResponseDTO.setChecked(roleIds.contains(roleDO.getId()));
            return userRoleResponseDTO;
        }).collect(Collectors.toList());
        return ResultUtil.success(result);
    }
}
