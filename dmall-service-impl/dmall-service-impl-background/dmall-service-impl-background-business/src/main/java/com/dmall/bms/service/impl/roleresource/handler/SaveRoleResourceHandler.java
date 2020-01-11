package com.dmall.bms.service.impl.roleresource.handler;

import com.dmall.bms.api.dto.roleresource.request.SaveRoleResourceRequestDTO;
import com.dmall.bms.generator.dataobject.RoleResourceDO;
import com.dmall.bms.generator.mapper.RoleResourceMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增后台角色-资源处理器
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Component
public class SaveRoleResourceHandler extends AbstractCommonHandler<SaveRoleResourceRequestDTO, RoleResourceDO, Long> {

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public BaseResult<Long> validate(SaveRoleResourceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveRoleResourceRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
