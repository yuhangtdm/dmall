package com.dmall.bms.service.impl.rolepermission.handler;

import com.dmall.bms.api.dto.rolepermission.common.CommonRolePermissionResponseDTO;
import com.dmall.bms.api.dto.rolepermission.request.ListRolePermissionRequestDTO;
import com.dmall.bms.generator.dataobject.RolePermissionDO;
import com.dmall.bms.generator.mapper.RolePermissionMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 后台角色-资源列表处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class ListRolePermissionHandler extends AbstractCommonHandler<ListRolePermissionRequestDTO, RolePermissionDO, CommonRolePermissionResponseDTO> {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public BaseResult<List<CommonRolePermissionResponseDTO>> validate(ListRolePermissionRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonRolePermissionResponseDTO>> processor(ListRolePermissionRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
