package com.dmall.bms.service.impl.permission.handler;

import com.dmall.bms.api.dto.permission.response.PermissionResponseDTO;
import com.dmall.bms.api.enums.BackGroundErrorEnum;
import com.dmall.bms.generator.dataobject.PermissionDO;
import com.dmall.bms.generator.mapper.PermissionMapper;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询权限处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class GetPermissionHandler extends AbstractCommonHandler<Long, PermissionDO, PermissionResponseDTO> {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public BaseResult<PermissionResponseDTO> processor(Long id) {
        PermissionDO permissionDO = permissionMapper.selectById(id);
        if (permissionDO == null) {
            return ResultUtil.fail(BackGroundErrorEnum.PERMISSION_NOT_EXIST);
        }
        return ResultUtil.success(doConvertDto(permissionDO, PermissionResponseDTO.class));
    }

}
