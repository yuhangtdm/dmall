package com.dmall.bms.service.impl.permission.handler;

import com.dmall.bms.api.dto.permission.request.UpdatePermissionRequestDTO;
import com.dmall.bms.service.impl.permission.enums.PermissionErrorEnum;
import com.dmall.bms.generator.dataobject.PermissionDO;
import com.dmall.bms.generator.mapper.PermissionMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改权限处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class UpdatePermissionHandler extends AbstractCommonHandler<UpdatePermissionRequestDTO, PermissionDO, Long> {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public BaseResult<Long> validate(UpdatePermissionRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdatePermissionRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}