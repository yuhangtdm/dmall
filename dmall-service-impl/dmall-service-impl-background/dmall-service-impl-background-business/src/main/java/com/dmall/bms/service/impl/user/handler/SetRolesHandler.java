package com.dmall.bms.service.impl.user.handler;

import com.dmall.bms.api.dto.user.request.SetRolesRequestDTO;
import com.dmall.bms.generator.dataobject.RoleDO;
import com.dmall.bms.generator.dataobject.UserDO;
import com.dmall.bms.generator.dataobject.UserRoleDO;
import com.dmall.bms.generator.mapper.RoleMapper;
import com.dmall.bms.generator.mapper.UserMapper;
import com.dmall.bms.generator.mapper.UserRoleMapper;
import com.dmall.bms.service.impl.support.UserRoleSupport;
import com.dmall.bms.api.enums.UserErrorEnum;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: SetRolesHandler
 * @author: created by hang.yu on 2020/2/20 20:51
 */
@Component
public class SetRolesHandler extends AbstractCommonHandler<SetRolesRequestDTO, UserRoleDO, Long> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserRoleSupport userRoleSupport;

    @Override
    public BaseResult<Long> validate(SetRolesRequestDTO requestDTO) {
        // 用户id必须存在
        UserDO userDO = userMapper.selectById(requestDTO.getUserId());
        if (userDO == null){
            return ResultUtil.fail(UserErrorEnum.USER_NOT_EXIST);
        }
        // 角色id集合必须存在
        List<RoleDO> roleDOS = roleMapper.selectBatchIds(requestDTO.getRoleIds());
        if (roleDOS.size() != requestDTO.getRoleIds().size()){
            return ResultUtil.fail(UserErrorEnum.ROLE_ID_NOT_EXIST);
        }

        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SetRolesRequestDTO requestDTO) {
        // 先删除后插入
        userRoleSupport.deleteByUserId(requestDTO.getUserId());
        for (Long roleId : requestDTO.getRoleIds()) {
            UserRoleDO userRoleDO = new UserRoleDO()
                    .setUserId(requestDTO.getUserId())
                    .setRoleId(roleId);
            userRoleMapper.insert(userRoleDO);
        }
        return ResultUtil.success(requestDTO.getUserId());
    }
}
