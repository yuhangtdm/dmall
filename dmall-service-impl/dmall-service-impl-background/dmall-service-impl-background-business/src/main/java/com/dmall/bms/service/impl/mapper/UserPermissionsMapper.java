package com.dmall.bms.service.impl.mapper;

import java.util.List;

/**
 * @description: UserPermissionsMapper
 * @author: created by hang.yu on 2020/2/20 22:32
 */
public interface UserPermissionsMapper {

    List<Long> listByUserId(Long userId);

    List<Long> listAllByUserId(Long userId);
}
