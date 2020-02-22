package com.dmall.bms.service.impl.mapper;

import com.dmall.bms.api.dto.menu.response.MenuTreeResponseDTO;

import java.util.List;

/**
 * @description: UserPermissionsMapper
 * @author: created by hang.yu on 2020/2/20 22:32
 */
public interface UserPermissionsMapper {

    List<MenuTreeResponseDTO > listByUserId(Long userId);
}
