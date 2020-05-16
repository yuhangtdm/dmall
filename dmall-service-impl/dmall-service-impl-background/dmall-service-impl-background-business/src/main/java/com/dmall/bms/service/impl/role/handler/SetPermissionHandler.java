package com.dmall.bms.service.impl.role.handler;

import cn.hutool.core.collection.CollUtil;
import com.dmall.bms.api.enums.BackGroundErrorEnum;
import com.dmall.bms.generator.dataobject.PermissionDO;
import com.dmall.bms.generator.dataobject.RoleDO;
import com.dmall.bms.generator.dataobject.RolePermissionDO;
import com.dmall.bms.generator.mapper.PermissionMapper;
import com.dmall.bms.generator.mapper.RoleMapper;
import com.dmall.bms.generator.mapper.RolePermissionMapper;
import com.dmall.bms.service.support.RolePermissionSupport;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.CheckedDTO;
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
public class SetPermissionHandler extends AbstractCommonHandler<CheckedDTO, RolePermissionDO, Long> {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private RolePermissionSupport rolePermissionSupport;

    @Override
    public BaseResult<Long> validate(CheckedDTO requestDTO) {
        // 角色必须存在
        RoleDO roleDO = roleMapper.selectById(requestDTO.getId());
        if (roleDO == null) {
            return ResultUtil.fail(BackGroundErrorEnum.ROLE_NOT_EXIST);
        }
        // 权限必须存在
        List<PermissionDO> permissionDOS = permissionMapper.selectBatchIds(requestDTO.getRelateIds());
        if (permissionDOS.size() != requestDTO.getRelateIds().size()) {
            return ResultUtil.fail(BackGroundErrorEnum.PERMISSION_NOT_EXIST);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(CheckedDTO requestDTO) {
        List<RolePermissionDO> listByRoleId = rolePermissionSupport.listByRoleId(requestDTO.getId());
        Set<Long> permissionIds = requestDTO.getRelateIds();
        // 角色列表为空 只是新增
        if (CollUtil.isEmpty(listByRoleId)) {
            for (Long permissionId : permissionIds) {
                RolePermissionDO rolePermissionDO = new RolePermissionDO()
                        .setRoleId(requestDTO.getId())
                        .setPermissionId(permissionId);
                rolePermissionMapper.insert(rolePermissionDO);
            }
        } else {
            // 先删后增
            List<Long> oldPermissionIds = listByRoleId.stream().map(RolePermissionDO::getPermissionId)
                    .collect(Collectors.toList());
            List<Long> insertPermissionIds = permissionIds.stream()
                    .filter(permissionId -> !oldPermissionIds.contains(permissionId))
                    .collect(Collectors.toList());
            List<Long> deletePermissionIds = oldPermissionIds.stream()
                    .filter(permissionId -> !permissionIds.contains(permissionId))
                    .collect(Collectors.toList());
            if (CollUtil.isNotEmpty(deletePermissionIds)) {
                rolePermissionSupport.delete(requestDTO.getId(), deletePermissionIds);
            }
            if (CollUtil.isNotEmpty(insertPermissionIds)) {
                for (Long permissionId : insertPermissionIds) {
                    RolePermissionDO rolePermissionDO = new RolePermissionDO()
                            .setRoleId(requestDTO.getId())
                            .setPermissionId(permissionId);
                    rolePermissionMapper.insert(rolePermissionDO);
                }
            }
        }

        return ResultUtil.success(requestDTO.getId());
    }
}
