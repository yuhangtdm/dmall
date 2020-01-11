package com.dmall.bms.service.impl.role.handler;

import com.dmall.bms.api.dto.role.request.UpdateRoleRequestDTO;
import com.dmall.bms.generator.dataobject.RoleDO;
import com.dmall.bms.generator.mapper.RoleMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改后台角色处理器
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Component
public class UpdateRoleHandler extends AbstractCommonHandler<UpdateRoleRequestDTO, RoleDO, Long> {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public BaseResult<Long> validate(UpdateRoleRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateRoleRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}