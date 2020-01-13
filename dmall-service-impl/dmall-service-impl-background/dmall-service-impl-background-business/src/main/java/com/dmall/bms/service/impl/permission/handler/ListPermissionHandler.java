package com.dmall.bms.service.impl.permission.handler;

import com.dmall.bms.api.dto.permission.common.CommonPermissionResponseDTO;
import com.dmall.bms.api.dto.permission.request.ListPermissionRequestDTO;
import com.dmall.bms.generator.dataobject.PermissionDO;
import com.dmall.bms.generator.mapper.PermissionMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 资源列表处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class ListPermissionHandler extends AbstractCommonHandler<ListPermissionRequestDTO, PermissionDO, CommonPermissionResponseDTO> {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public BaseResult<List<CommonPermissionResponseDTO>> validate(ListPermissionRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonPermissionResponseDTO>> processor(ListPermissionRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
