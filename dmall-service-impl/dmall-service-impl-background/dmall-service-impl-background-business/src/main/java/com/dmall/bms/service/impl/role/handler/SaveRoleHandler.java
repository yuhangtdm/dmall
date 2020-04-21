package com.dmall.bms.service.impl.role.handler;

import com.dmall.bms.api.dto.role.request.SaveRoleRequestDTO;
import com.dmall.bms.api.enums.BackGroundErrorEnum;
import com.dmall.bms.generator.dataobject.RoleDO;
import com.dmall.bms.generator.mapper.RoleMapper;
import com.dmall.bms.service.impl.support.RoleSupport;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增后台角色处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class SaveRoleHandler extends AbstractCommonHandler<SaveRoleRequestDTO, RoleDO, Long> {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleSupport roleSupport;

    @Override
    public BaseResult<Long> validate(SaveRoleRequestDTO requestDTO) {
        // 角色名称必须唯一
        RoleDO roleDO = roleSupport.getByName(requestDTO.getName());
        if (roleDO != null) {
            return ResultUtil.fail(BackGroundErrorEnum.NAME_EXIST);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveRoleRequestDTO requestDTO) {
        RoleDO roleDO = dtoConvertDo(requestDTO, RoleDO.class);
        roleMapper.insert(roleDO);
        return ResultUtil.success(roleDO.getId());
    }

}
