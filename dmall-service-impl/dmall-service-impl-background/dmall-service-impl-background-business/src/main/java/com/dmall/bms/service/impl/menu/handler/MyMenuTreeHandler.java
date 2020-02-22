package com.dmall.bms.service.impl.menu.handler;

import com.dmall.bms.api.dto.menu.response.MenuTreeResponseDTO;
import com.dmall.bms.generator.dataobject.MenuDO;
import com.dmall.bms.service.impl.mapper.UserMenusMapper;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.model.user.AdminUserContextHolder;
import com.dmall.common.model.user.UserDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
        UserDTO userDTO = AdminUserContextHolder.get();
        List<MenuTreeResponseDTO> allMenus = userMenuMapper.listByUserId(userDTO.getId());

        Map<Long, MenuTreeResponseDTO> map = Maps.newHashMap();
        List<MenuTreeResponseDTO> resultMenus = Lists.newArrayList();

        for (MenuTreeResponseDTO menu : allMenus) {
            if (menu.getParentId().equals(0L)) {
                resultMenus.add(menu);
            }
            map.put(menu.getId(), menu);
        }

        for (MenuTreeResponseDTO menu : allMenus) {
            if (map.get(menu.getParentId()) != null) {
                map.get(menu.getParentId()).getChild().add(menu);
            }
        }

        return ResultUtil.success(resultMenus);
    }
}
