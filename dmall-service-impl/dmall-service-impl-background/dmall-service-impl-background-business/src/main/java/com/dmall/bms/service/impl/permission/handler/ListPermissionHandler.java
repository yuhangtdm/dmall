package com.dmall.bms.service.impl.permission.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dmall.bms.api.dto.permission.request.ListPermissionRequestDTO;
import com.dmall.bms.api.dto.permission.response.PermissionResponseDTO;
import com.dmall.bms.generator.dataobject.PermissionDO;
import com.dmall.bms.generator.mapper.PermissionMapper;
import com.dmall.bms.service.support.PermissionSupport;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 权限列表处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class ListPermissionHandler
    extends AbstractCommonHandler<ListPermissionRequestDTO, PermissionDO, PermissionResponseDTO> {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public BaseResult<List<PermissionResponseDTO>> processor(ListPermissionRequestDTO requestDTO) {
        LambdaQueryWrapper<PermissionDO> wrapper = PermissionSupport.buildWrapper(requestDTO.getAppId(),
            requestDTO.getBusiness(), requestDTO.getName(), requestDTO.getUri(), requestDTO.getMethod());
        List<PermissionDO> permissionList = permissionMapper.selectList(wrapper);

        List<PermissionResponseDTO> collect = permissionList.stream()
            .map(permissionDO -> doConvertDto(permissionDO, PermissionResponseDTO.class))
            .collect(Collectors.toList());
        return ResultUtil.success(collect);
    }

}
