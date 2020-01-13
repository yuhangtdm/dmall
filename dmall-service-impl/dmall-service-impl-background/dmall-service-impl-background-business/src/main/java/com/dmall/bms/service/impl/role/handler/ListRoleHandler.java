package com.dmall.bms.service.impl.role.handler;

import com.dmall.bms.api.dto.role.common.CommonRoleResponseDTO;
import com.dmall.bms.api.dto.role.request.ListRoleRequestDTO;
import com.dmall.bms.generator.dataobject.RoleDO;
import com.dmall.bms.generator.mapper.RoleMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 后台角色列表处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class ListRoleHandler extends AbstractCommonHandler<ListRoleRequestDTO, RoleDO, CommonRoleResponseDTO> {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public BaseResult<List<CommonRoleResponseDTO>> validate(ListRoleRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonRoleResponseDTO>> processor(ListRoleRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
