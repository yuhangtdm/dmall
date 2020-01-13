package com.dmall.bms.service.impl.permission.handler;

import com.dmall.bms.api.dto.permission.common.CommonPermissionResponseDTO;
import com.dmall.bms.service.impl.permission.enums.PermissionErrorEnum;
import com.dmall.bms.generator.dataobject.PermissionDO;
import com.dmall.bms.generator.mapper.PermissionMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询资源处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class GetPermissionHandler extends AbstractCommonHandler<Long, PermissionDO, CommonPermissionResponseDTO> {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public BaseResult<CommonPermissionResponseDTO> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<CommonPermissionResponseDTO> processor(Long id) {
        return ResultUtil.success();
    }

}
