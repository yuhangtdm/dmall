package com.dmall.bms.service.impl.permission.handler;

import com.dmall.bms.api.dto.permission.request.PagePermissionRequestDTO;
import com.dmall.bms.api.dto.permission.response.PagePermissionResponseDTO;
import com.dmall.bms.generator.dataobject.PermissionDO;
import com.dmall.bms.generator.mapper.PermissionMapper;
import com.dmall.bms.service.impl.support.PermissionSupport;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 权限分页处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class PagePermissionHandler extends AbstractCommonHandler<PagePermissionRequestDTO, PermissionDO, PagePermissionResponseDTO> {

    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public BaseResult<LayUiPage<PagePermissionResponseDTO>> processor(PagePermissionRequestDTO requestDTO) {

        PermissionSupport.buildWrapper(requestDTO.getParentId(), requestDTO.getCode(), requestDTO.getName()
        ,requestDTO.getUri(), requestDTO.getMethod());

        return ResultUtil.success();
    }

}
