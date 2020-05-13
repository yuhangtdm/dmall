package com.dmall.bms.service.impl.menu.handler;

import com.dmall.bms.api.dto.menu.response.MenuTreeResponseDTO;
import com.dmall.bms.generator.dataobject.MenuDO;
import com.dmall.bms.service.impl.mapper.UserMenusMapper;
import com.dmall.bms.service.impl.support.MenuSupport;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.admin.AdminUserContextHolder;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: MyMenuTreeHandler
 * @author: created by hang.yu on 2020/2/22 15:57
 */
@Component
public class MyMenuTreeHandler extends AbstractCommonHandler<Void, MenuDO, MenuTreeResponseDTO> {

    @Autowired
    private UserMenusMapper userMenuMapper;

    @Override
    public BaseResult<List<MenuTreeResponseDTO>> processor(Void aVoid) {
        // 获取当前登录的用户
        AdminUserDTO adminUserDTO = AdminUserContextHolder.get();
        List<MenuTreeResponseDTO> allMenus = userMenuMapper.listByUserId(adminUserDTO.getId());
        return ResultUtil.success(MenuSupport.getMenuTrees(allMenus));
    }

}
