package com.dmall.bms.service.impl.roleresource.handler;

import com.dmall.bms.api.dto.roleresource.common.CommonRoleResourceResponseDTO;
import com.dmall.bms.generator.dataobject.RoleResourceDO;
import com.dmall.bms.generator.mapper.RoleResourceMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询后台角色-资源处理器
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Component
public class GetRoleResourceHandler extends AbstractCommonHandler<Long, RoleResourceDO, CommonRoleResourceResponseDTO> {

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public BaseResult<CommonRoleResourceResponseDTO> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<CommonRoleResourceResponseDTO> processor(Long id) {
        return ResultUtil.success();
    }

}
