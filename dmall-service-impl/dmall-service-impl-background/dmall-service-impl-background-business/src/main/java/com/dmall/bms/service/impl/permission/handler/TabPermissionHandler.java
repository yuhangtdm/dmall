package com.dmall.bms.service.impl.permission.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.bms.api.dto.permission.response.tab.PermissionDTO;
import com.dmall.bms.api.dto.permission.response.tab.TabPermissionResponseDTO;
import com.dmall.bms.api.enums.AppEnum;
import com.dmall.bms.generator.dataobject.PermissionDO;
import com.dmall.bms.generator.dataobject.RolePermissionDO;
import com.dmall.bms.generator.mapper.PermissionMapper;
import com.dmall.bms.service.impl.support.RolePermissionSupport;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: TabPermissionHandler
 * @author: created by hang.yu on 2020/5/16 14:30
 */
@Component
public class TabPermissionHandler extends AbstractCommonHandler<Long, PermissionDO, TabPermissionResponseDTO> {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionSupport rolePermissionSupport;

    @Override
    public BaseResult<List<TabPermissionResponseDTO>> processor(Long roleId) {
        List<PermissionDO> permissionList = permissionMapper.selectList(Wrappers.emptyWrapper());
        Map<String, List<PermissionDO>> collect = permissionList.stream()
                .collect(Collectors.groupingBy(PermissionDO::getAppId, Collectors.toList()));
        List<Long> permissionIds = rolePermissionSupport.listByRoleId(roleId).stream()
                .map(RolePermissionDO::getPermissionId)
                .collect(Collectors.toList());

        List<TabPermissionResponseDTO> result = Lists.newArrayList();
        collect.forEach((k, v) -> {
            TabPermissionResponseDTO response = new TabPermissionResponseDTO();
            response.setAppId(k);
            response.setAppName(EnumUtil.getDesc(AppEnum.class, k));
            List<PermissionDTO> permissions = v.stream().map(permission -> {
                PermissionDTO permissionDTO = new PermissionDTO();
                permissionDTO.setId(permission.getId());
                permissionDTO.setName(permission.getName());
                permissionDTO.setChecked(permissionIds.contains(permission.getId()));
                return permissionDTO;
            }).collect(Collectors.toList());
            response.setPermissions(permissions);
            result.add(response);
        });
        return ResultUtil.success(result);
    }
}
