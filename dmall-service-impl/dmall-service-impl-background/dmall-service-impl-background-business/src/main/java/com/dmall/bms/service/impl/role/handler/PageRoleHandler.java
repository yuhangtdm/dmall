package com.dmall.bms.service.impl.role.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.bms.api.dto.role.request.PageRoleRequestDTO;
import com.dmall.bms.api.dto.role.response.RoleResponseDTO;
import com.dmall.bms.generator.dataobject.RoleDO;
import com.dmall.bms.generator.mapper.RoleMapper;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 后台角色分页处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class PageRoleHandler extends AbstractCommonHandler<PageRoleRequestDTO, RoleDO, RoleResponseDTO> {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public BaseResult<ResponsePage<RoleResponseDTO>> processor(PageRoleRequestDTO requestDTO) {
        IPage<RoleDO> page = new Page<>(requestDTO.getCurrent(), requestDTO.getSize());
        LambdaQueryWrapper<RoleDO> wrapper = Wrappers.<RoleDO>lambdaQuery()
            .like(StrUtil.isNotBlank(requestDTO.getName()), RoleDO::getName, requestDTO.getName());

        page = roleMapper.selectPage(page, wrapper);

        List<RoleResponseDTO> collect = page.getRecords().stream()
            .map(roleDO -> doConvertDto(roleDO, RoleResponseDTO.class))
            .collect(Collectors.toList());

        return ResultUtil.success(new ResponsePage<>(page.getTotal(), collect));
    }

}
