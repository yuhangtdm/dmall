package com.dmall.bms.service.impl.role.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.bms.api.dto.role.request.ListRoleRequestDTO;
import com.dmall.bms.api.dto.role.response.RoleResponseDTO;
import com.dmall.bms.generator.dataobject.RoleDO;
import com.dmall.bms.generator.mapper.RoleMapper;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 后台角色列表处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class ListRoleHandler extends AbstractCommonHandler<ListRoleRequestDTO, RoleDO, RoleResponseDTO> {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public BaseResult<List<RoleResponseDTO>> processor(ListRoleRequestDTO requestDTO) {
        List<RoleDO> roleList = roleMapper.selectList(Wrappers.<RoleDO>lambdaQuery()
            .eq(StrUtil.isNotBlank(requestDTO.getName()), RoleDO::getName, requestDTO.getName()));
        List<RoleResponseDTO> collect = roleList.stream()
            .map(roleDO -> doConvertDto(roleDO, RoleResponseDTO.class))
            .collect(Collectors.toList());
        return ResultUtil.success(collect);
    }

}
