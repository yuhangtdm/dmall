package com.dmall.bms.service.impl.role.handler;

import com.dmall.bms.api.dto.role.common.CommonRoleResponseDTO;
import com.dmall.bms.service.impl.role.enums.RoleErrorEnum;
import com.dmall.bms.generator.dataobject.RoleDO;
import com.dmall.bms.generator.mapper.RoleMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询后台角色处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class GetRoleHandler extends AbstractCommonHandler<Long, RoleDO, CommonRoleResponseDTO> {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public BaseResult<CommonRoleResponseDTO> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<CommonRoleResponseDTO> processor(Long id) {
        return ResultUtil.success();
    }

}
