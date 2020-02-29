package com.dmall.bms.service.impl.role.handler;

import cn.hutool.core.collection.CollUtil;
import com.dmall.bms.api.dto.role.request.SetPermissionsRequestDTO;
import com.dmall.bms.generator.dataobject.PermissionDO;
import com.dmall.bms.generator.dataobject.RoleDO;
import com.dmall.bms.generator.dataobject.RolePermissionDO;
import com.dmall.bms.generator.mapper.PermissionMapper;
import com.dmall.bms.generator.mapper.RoleMapper;
import com.dmall.bms.generator.mapper.RolePermissionMapper;
import com.dmall.bms.api.enums.RoleErrorEnum;
import com.dmall.bms.service.impl.support.RolePermissionSupport;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: SetPermissionsHandler
 * @author: created by hang.yu on 2020/2/20 21:52
 */
@Component
public class SetPermissionsHandler extends AbstractCommonHandler<SetPermissionsRequestDTO, RolePermissionDO, Long>  {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private RolePermissionSupport rolePermissionSupport;

    @Override
    public BaseResult<Long> validate(SetPermissionsRequestDTO requestDTO ) {
        // 角色必须存在
        RoleDO roleDO = roleMapper.selectById(requestDTO.getRoleId());
        if (roleDO == null){
            return ResultUtil.fail(RoleErrorEnum.ROLE_NOT_EXIST);
        }
        // 权限必须存在
        List<PermissionDO> permissionDOS = permissionMapper.selectBatchIds(requestDTO.getPermissionIds());
        if (permissionDOS.size() != requestDTO.getPermissionIds().size()){
            return ResultUtil.fail(RoleErrorEnum.PERMISSION_NOT_EXIST);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SetPermissionsRequestDTO requestDTO) {
        List<RolePermissionDO> listByRoleId = rolePermissionSupport.listByRoleId(requestDTO.getRoleId());
        Set<Long> permissionIds = requestDTO.getPermissionIds();
        if (CollUtil.isEmpty(listByRoleId)){
            for (Long permissionId : permissionIds) {
                RolePermissionDO rolePermissionDO = new RolePermissionDO()
                        .setRoleId(requestDTO.getRoleId())
                        .setPermissionId(permissionId);
                rolePermissionMapper.insert(rolePermissionDO);
            }
        }else {
            List<Long> oldPermissionIds = listByRoleId.stream().map(RolePermissionDO::getPermissionId).collect(Collectors.toList());
            List<Long> insertPermissionIds = permissionIds.stream()
                    .filter(permissionId -> !oldPermissionIds.contains(permissionId))
                    .collect(Collectors.toList());
            List<Long> deletePermissionIds = oldPermissionIds.stream().filter(permissionId -> !permissionIds.contains(permissionId))
                    .collect(Collectors.toList());
            if (CollUtil.isNotEmpty(deletePermissionIds)){
                rolePermissionSupport.delete(requestDTO.getRoleId(), deletePermissionIds);
            }
            if (CollUtil.isNotEmpty(insertPermissionIds)){
                for (Long permissionId : insertPermissionIds) {
                    RolePermissionDO rolePermissionDO = new RolePermissionDO()
                            .setRoleId(requestDTO.getRoleId())
                            .setPermissionId(permissionId);
                    rolePermissionMapper.insert(rolePermissionDO);
                }
            }
        }

        return ResultUtil.success(requestDTO.getRoleId());
    }
}
