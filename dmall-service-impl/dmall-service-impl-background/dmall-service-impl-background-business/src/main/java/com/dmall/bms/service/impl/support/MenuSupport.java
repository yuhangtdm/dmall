package com.dmall.bms.service.impl.support;

import com.dmall.bms.api.dto.menu.response.MenuTreeResponseDTO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * @description: MenuSupport
 * @author: created by hang.yu on 2020/4/21 23:02
 */
public class MenuSupport {

    public static List<MenuTreeResponseDTO> getMenuTrees(List<MenuTreeResponseDTO> allMenus) {
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
                map.get(menu.getParentId()).getChildren().add(menu);
            }
        }
        return resultMenus;
    }
}
