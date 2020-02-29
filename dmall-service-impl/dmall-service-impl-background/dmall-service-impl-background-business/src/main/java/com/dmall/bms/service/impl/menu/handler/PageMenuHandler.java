package com.dmall.bms.service.impl.menu.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.bms.api.dto.menu.response.PageMenuResponseDTO;
import com.dmall.bms.api.dto.menu.request.PageMenuRequestDTO;
import com.dmall.bms.generator.dataobject.MenuDO;
import com.dmall.bms.generator.mapper.MenuMapper;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 菜单表 分页处理器
 * @author: created by hang.yu on 2020-02-20 21:36:43
 */
@Component
public class PageMenuHandler extends AbstractCommonHandler<PageMenuRequestDTO, MenuDO, PageMenuResponseDTO> {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public BaseResult<ResponsePage<PageMenuResponseDTO>> processor(PageMenuRequestDTO requestDTO) {
        IPage<MenuDO> page = new Page<>(requestDTO.getCurrent(), requestDTO.getSize());
        LambdaQueryWrapper<MenuDO> wrapper = Wrappers.<MenuDO>lambdaQuery()
                .eq(requestDTO.getParentId() != null, MenuDO::getParentId, requestDTO.getParentId())
                .like(StrUtil.isNotBlank(requestDTO.getName()), MenuDO::getName, requestDTO.getName())
                .eq(requestDTO.getType() != null, MenuDO::getType, requestDTO.getType())
                .like(StrUtil.isNotBlank(requestDTO.getUrl()), MenuDO::getUrl, requestDTO.getUrl());
        page = menuMapper.selectPage(page, wrapper);

        List<PageMenuResponseDTO> collect = page.getRecords().stream().map(menuDO -> doConvertDto(menuDO, PageMenuResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(new ResponsePage<>(page.getTotal(), collect));
    }

}
