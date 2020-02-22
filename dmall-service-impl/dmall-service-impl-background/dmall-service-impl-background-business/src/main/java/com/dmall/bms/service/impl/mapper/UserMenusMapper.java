package com.dmall.bms.service.impl.mapper;

import com.dmall.bms.api.dto.menu.response.MenuTreeResponseDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @description: UserMenuMapper
 * @author: created by hang.yu on 2020/2/22 16:10
 */
public interface UserMenusMapper {

    List<MenuTreeResponseDTO> listByUserId(@Param("userId") Long userId);
}
