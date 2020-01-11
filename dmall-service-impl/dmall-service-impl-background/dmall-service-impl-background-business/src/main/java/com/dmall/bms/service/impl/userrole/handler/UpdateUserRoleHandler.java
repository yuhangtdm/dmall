package com.dmall.bms.service.impl.userrole.handler;

import com.dmall.bms.api.dto.userrole.request.UpdateUserRoleRequestDTO;
import com.dmall.bms.generator.dataobject.UserRoleDO;
import com.dmall.bms.generator.mapper.UserRoleMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改后台用户-角色处理器
 * @author: created by hang.yu on 2020-01-05 18:36:38
 */
@Component
public class UpdateUserRoleHandler extends AbstractCommonHandler<UpdateUserRoleRequestDTO, UserRoleDO, Long> {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public BaseResult<Long> validate(UpdateUserRoleRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateUserRoleRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}