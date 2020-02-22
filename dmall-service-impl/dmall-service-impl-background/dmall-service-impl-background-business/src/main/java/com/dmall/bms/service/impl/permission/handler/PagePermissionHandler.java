package com.dmall.bms.service.impl.permission.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

import java.util.List;
import java.util.stream.Collectors;

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
        LambdaQueryWrapper wrapper = PermissionSupport.buildWrapper(requestDTO.getServiceId(), requestDTO.getCode(),
                requestDTO.getName(), requestDTO.getUri(), requestDTO.getMethod());

        IPage<PermissionDO> page = new Page<>(requestDTO.getCurrent(), requestDTO.getSize());
        page = permissionMapper.selectPage(page, wrapper);
        List<PagePermissionResponseDTO> collect = page.getRecords().stream()
                .map(userDO -> doConvertDto(userDO, PagePermissionResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(new LayUiPage<>(page.getTotal(), collect));
    }

}
