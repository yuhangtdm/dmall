package com.dmall.bms.service.impl.permission.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import java.util.stream.Collectors;

/**
 * @description: 权限列表处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class ListPermissionHandler extends AbstractCommonHandler<ListPermissionRequestDTO, PermissionDO, CommonPermissionResponseDTO> {

    @Autowired
    private PermissionMapper permissionMapper;



    @Override
    public BaseResult<List<CommonPermissionResponseDTO>> processor(ListPermissionRequestDTO requestDTO) {
        LambdaQueryWrapper<PermissionDO> wrapper = Wrappers.<PermissionDO>lambdaQuery()
                .eq(requestDTO.getParentId() != null, PermissionDO::getParentId, requestDTO.getParentId())
                .like(StrUtil.isNotBlank(requestDTO.getCode()), PermissionDO::getCode, requestDTO.getCode())
                .like(StrUtil.isNotBlank(requestDTO.getName()), PermissionDO::getName, requestDTO.getName())
                .eq(StrUtil.isNotBlank(requestDTO.getUri()), PermissionDO::getUri, requestDTO.getUri())
                .eq(StrUtil.isNotBlank(requestDTO.getMethod()), PermissionDO::getMethod, requestDTO.getMethod());
        List<PermissionDO> permissionList = permissionMapper.selectList(wrapper);

        List<CommonPermissionResponseDTO> collect = permissionList.stream()
                .map(permissionDO -> doConvertDto(permissionDO, CommonPermissionResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(collect);
    }

}
