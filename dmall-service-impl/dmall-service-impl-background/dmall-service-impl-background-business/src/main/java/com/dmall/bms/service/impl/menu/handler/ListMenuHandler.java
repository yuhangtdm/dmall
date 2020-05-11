package com.dmall.bms.service.impl.menu.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.bms.api.dto.menu.response.PageMenuResponseDTO;
import com.dmall.bms.generator.dataobject.MenuDO;
import com.dmall.bms.generator.mapper.MenuMapper;
import com.dmall.common.dto.BaseResult;
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
public class ListMenuHandler extends AbstractCommonHandler<Void, MenuDO, PageMenuResponseDTO> {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public BaseResult<List<PageMenuResponseDTO>> processor(Void v) {

        List<MenuDO> menuList = menuMapper.selectList(Wrappers.emptyWrapper());

        List<PageMenuResponseDTO> collect = menuList.stream()
                .map(menuDO -> doConvertDto(menuDO, PageMenuResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(collect);
    }

}
