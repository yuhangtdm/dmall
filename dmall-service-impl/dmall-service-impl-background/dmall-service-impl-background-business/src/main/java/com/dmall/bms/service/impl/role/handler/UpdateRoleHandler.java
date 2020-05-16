package com.dmall.bms.service.impl.role.handler;

import com.dmall.bms.api.dto.role.request.UpdateRoleRequestDTO;
import com.dmall.bms.api.enums.BackGroundErrorEnum;
import com.dmall.bms.generator.dataobject.RoleDO;
import com.dmall.bms.generator.mapper.RoleMapper;
import com.dmall.bms.service.support.RoleSupport;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改后台角色处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class UpdateRoleHandler extends AbstractCommonHandler<UpdateRoleRequestDTO, RoleDO, Long> {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleSupport roleSupport;

    @Override
    public BaseResult<Long> validate(UpdateRoleRequestDTO requestDTO) {
        RoleDO roleDO = roleMapper.selectById(requestDTO.getId());
        if (roleDO == null) {
            return ResultUtil.fail(BackGroundErrorEnum.ROLE_NOT_EXIST);
        }
        RoleDO byName = roleSupport.getByName(requestDTO.getName());
        if (byName != null && !byName.getId().equals(requestDTO.getId())) {
            return ResultUtil.fail(BackGroundErrorEnum.NAME_EXIST);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateRoleRequestDTO requestDTO) {
        RoleDO roleDO = dtoConvertDo(requestDTO, RoleDO.class);
        roleMapper.updateById(roleDO);
        return ResultUtil.success(roleDO.getId());
    }

}