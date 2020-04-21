package com.dmall.bms.service.impl.role.handler;

import com.dmall.bms.api.dto.role.response.RoleResponseDTO;
import com.dmall.bms.api.enums.BackGroundErrorEnum;
import com.dmall.bms.generator.dataobject.RoleDO;
import com.dmall.bms.generator.mapper.RoleMapper;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询后台角色处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class GetRoleHandler extends AbstractCommonHandler<Long, RoleDO, RoleResponseDTO> {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public BaseResult<RoleResponseDTO> processor(Long id) {
        RoleDO roleDO = roleMapper.selectById(id);
        if (roleDO == null) {
            return ResultUtil.fail(BackGroundErrorEnum.ROLE_NOT_EXIST);
        }
        return ResultUtil.success(doConvertDto(roleDO, RoleResponseDTO.class));
    }

}
