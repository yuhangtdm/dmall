package com.dmall.bms.service.impl.permission.handler;

import com.dmall.bms.api.dto.permission.common.CommonPermissionResponseDTO;
import com.dmall.bms.api.dto.permission.request.PagePermissionRequestDTO;
import com.dmall.bms.generator.dataobject.PermissionDO;
import com.dmall.bms.generator.mapper.PermissionMapper;
import com.dmall.common.dto.LayUiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 资源分页处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class PagePermissionHandler extends AbstractCommonHandler<PagePermissionRequestDTO, PermissionDO, CommonPermissionResponseDTO> {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public BaseResult<LayUiPage<CommonPermissionResponseDTO>> validate(PagePermissionRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonPermissionResponseDTO>> processor(PagePermissionRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
