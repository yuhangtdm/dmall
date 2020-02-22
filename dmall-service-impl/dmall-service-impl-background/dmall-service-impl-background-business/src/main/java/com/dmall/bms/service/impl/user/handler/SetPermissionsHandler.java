package com.dmall.bms.service.impl.user.handler;

import cn.hutool.core.collection.CollUtil;
import com.dmall.bms.api.dto.user.request.SetPermissionsExtRequestDTO;
import com.dmall.bms.generator.dataobject.PermissionDO;
import com.dmall.bms.generator.dataobject.UserDO;
import com.dmall.bms.generator.dataobject.UserPermissionDO;
import com.dmall.bms.generator.mapper.PermissionMapper;
import com.dmall.bms.generator.mapper.UserMapper;
import com.dmall.bms.generator.mapper.UserPermissionMapper;
import com.dmall.bms.service.impl.mapper.UserPermissionsMapper;
import com.dmall.bms.service.impl.user.enums.OperationEnum;
import com.dmall.bms.service.impl.user.enums.UserErrorEnum;
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
 * @author: created by hang.yu on 2020/2/20 22:23
 */
@Component
public class SetPermissionsHandler extends AbstractCommonHandler<SetPermissionsExtRequestDTO, UserPermissionDO, Long> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserPermissionsMapper userPermissionsMapper;

    @Autowired
    private UserPermissionMapper userPermissionMapper;

    @Override
    public BaseResult<Long> validate(SetPermissionsExtRequestDTO requestDTO) {
        // 用户必须存在
        UserDO userDO = userMapper.selectById(requestDTO.getUserId());
        if (userDO == null) {
            return ResultUtil.fail(UserErrorEnum.USER_NOT_EXIST);
        }
        // 权限必须存在
        List<PermissionDO> permissionDOS = permissionMapper.selectBatchIds(requestDTO.getPermissionIds());
        if (permissionDOS.size() != requestDTO.getPermissionIds().size()) {
            return ResultUtil.fail(UserErrorEnum.PERMISSION_ID_NOT_EXIST);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SetPermissionsExtRequestDTO requestDTO) {
        // 查询用户的权限
        Set<Long> permissionIds = requestDTO.getPermissionIds();
        List<Long> oldPermissionIds = userPermissionsMapper.listByUserId(requestDTO.getUserId());
        List<Long> deletePermissionIds = oldPermissionIds.stream().filter(permissionId -> !permissionIds.contains(permissionId))
                .collect(Collectors.toList());
        List<Long> insertPermissionIds = permissionIds.stream().filter(permissionId -> !oldPermissionIds.contains(permissionId))
                .collect(Collectors.toList());
        if (CollUtil.isNotEmpty(deletePermissionIds)) {
            for (Long permissionId : deletePermissionIds) {
                UserPermissionDO userPermissionDO = new UserPermissionDO()
                        .setUserId(requestDTO.getUserId())
                        .setPermissionId(permissionId)
                        .setType(OperationEnum.SUB.getCode());
                userPermissionMapper.insert(userPermissionDO);
            }
        }
        if (CollUtil.isNotEmpty(insertPermissionIds)) {
            for (Long permissionId : insertPermissionIds) {
                UserPermissionDO userPermissionDO = new UserPermissionDO()
                        .setUserId(requestDTO.getUserId())
                        .setPermissionId(permissionId)
                        .setType(OperationEnum.ADD.getCode());
                userPermissionMapper.insert(userPermissionDO);
            }
        }
        return ResultUtil.success(requestDTO.getUserId());
    }
}
