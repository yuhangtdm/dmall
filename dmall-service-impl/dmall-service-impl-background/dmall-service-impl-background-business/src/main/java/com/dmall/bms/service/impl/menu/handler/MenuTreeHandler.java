package com.dmall.bms.service.impl.menu.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.bms.api.dto.menu.response.MenuTreeResponseDTO;
import com.dmall.bms.generator.dataobject.MenuDO;
import com.dmall.bms.generator.mapper.MenuMapper;
import com.dmall.bms.service.support.MenuSupport;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: MenuTreeHandler
 * @author: created by hang.yu on 2020/2/22 15:25
 */
@Component
public class MenuTreeHandler extends AbstractCommonHandler<Void, MenuDO, MenuTreeResponseDTO> {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public BaseResult<List<MenuTreeResponseDTO>> processor(Void aVoid) {
        // 查询所有菜单
        List<MenuTreeResponseDTO> allMenus = menuMapper.selectList(Wrappers.emptyWrapper()).stream()
                .map(menuDO -> doConvertDto(menuDO, MenuTreeResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(MenuSupport.getMenuTrees(allMenus));
    }
}
