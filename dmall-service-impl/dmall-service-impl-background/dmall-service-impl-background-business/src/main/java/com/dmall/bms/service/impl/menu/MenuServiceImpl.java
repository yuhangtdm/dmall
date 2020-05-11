package com.dmall.bms.service.impl.menu;

import com.dmall.bms.api.dto.menu.response.GetMenuResponseDTO;
import com.dmall.bms.api.dto.menu.response.MenuTreeResponseDTO;
import com.dmall.bms.api.dto.menu.response.PageMenuResponseDTO;
import com.dmall.bms.api.dto.menu.request.PageMenuRequestDTO;
import com.dmall.bms.api.dto.menu.request.SaveMenuRequestDTO;
import com.dmall.bms.api.dto.menu.request.UpdateMenuRequestDTO;
import com.dmall.bms.api.service.MenuService;
import com.dmall.bms.service.impl.menu.handler.*;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 菜单表 服务实现
 * @author: created by hang.yu on 2020-02-20 21:36:43
 */
@RestController
public class MenuServiceImpl implements MenuService {

    @Autowired
    private SaveMenuHandler saveMenuHandler;

    @Autowired
    private DeleteMenuHandler deleteMenuHandler;

    @Autowired
    private UpdateMenuHandler updateMenuHandler;

    @Autowired
    private GetMenuHandler getMenuHandler;

    @Autowired
    private PageMenuHandler pageMenuHandler;

    @Autowired
    private MenuTreeHandler menuTreeHandler;

    @Autowired
    private MyMenuTreeHandler myMenuTreeHandler;

    @Autowired
    private ListMenuHandler listMenuHandler;

    @Override
    public BaseResult<Long> save(@RequestBody SaveMenuRequestDTO requestDTO) {
        return saveMenuHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteMenuHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateMenuRequestDTO requestDTO) {
        return updateMenuHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<GetMenuResponseDTO> get(Long id) {
        return getMenuHandler.handler(id);
    }

    @Override
    public BaseResult<ResponsePage<PageMenuResponseDTO>> page(@RequestBody PageMenuRequestDTO requestDTO) {
        return pageMenuHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<List<PageMenuResponseDTO>> list() {
        return listMenuHandler.handler(null);
    }

    @Override
    public BaseResult<List<MenuTreeResponseDTO>> tree() {
        return menuTreeHandler.handler(null);
    }

    @Override
    public BaseResult<List<MenuTreeResponseDTO>> myTree() {
        return myMenuTreeHandler.handler(null);
    }

}
