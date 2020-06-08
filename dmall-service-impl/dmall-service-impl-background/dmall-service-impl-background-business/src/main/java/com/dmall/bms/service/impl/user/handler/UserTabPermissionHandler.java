package com.dmall.bms.service.impl.user.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.bms.api.dto.permission.response.tab.TabPermissionResponseDTO;
import com.dmall.bms.generator.dataobject.PermissionDO;
import com.dmall.bms.generator.dataobject.UserPermissionDO;
import com.dmall.bms.generator.mapper.PermissionMapper;
import com.dmall.bms.service.impl.mapper.UserPermissionsMapper;
import com.dmall.bms.service.support.PermissionSupport;
import com.dmall.bms.service.support.UserPermissionSupport;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: TabPermissionHandler
 * @author: created by hang.yu on 2020/5/16 14:30
 */
@Component
public class UserTabPermissionHandler extends AbstractCommonHandler<Long, PermissionDO, TabPermissionResponseDTO> {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserPermissionsMapper userPermissionsMapper;

    @Autowired
    private UserPermissionSupport userPermissionSupport;

    @Override
    public BaseResult<List<TabPermissionResponseDTO>> processor(Long userId) {
        // 获取所有的权限
        List<PermissionDO> permissionList = permissionMapper.selectList(Wrappers.emptyWrapper());
        Map<String, List<PermissionDO>> collect = permissionList.stream()
            .collect(Collectors.groupingBy(PermissionDO::getAppId, Collectors.toList()));
        // 获取用户的权限
        List<Long> permissionIds = userPermissionsMapper.listAllByUserId(userId);
        List<Long> subPermissionIds = userPermissionSupport.listSubByUserId(userId).stream()
            .map(UserPermissionDO::getPermissionId)
            .collect(Collectors.toList());
        // 去除减权限
        permissionIds.removeAll(subPermissionIds);
        List<TabPermissionResponseDTO> result = PermissionSupport.getTabPermissionResponseDTOS(collect, permissionIds);
        return ResultUtil.success(result);
    }

}
