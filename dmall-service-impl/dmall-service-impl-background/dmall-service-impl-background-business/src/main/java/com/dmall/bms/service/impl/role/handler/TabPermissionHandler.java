package com.dmall.bms.service.impl.role.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.bms.api.dto.permission.response.tab.TabPermissionResponseDTO;
import com.dmall.bms.generator.dataobject.PermissionDO;
import com.dmall.bms.generator.dataobject.RolePermissionDO;
import com.dmall.bms.generator.mapper.PermissionMapper;
import com.dmall.bms.service.support.PermissionSupport;
import com.dmall.bms.service.support.RolePermissionSupport;
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
public class TabPermissionHandler extends AbstractCommonHandler<Long, PermissionDO, TabPermissionResponseDTO> {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionSupport rolePermissionSupport;

    @Override
    public BaseResult<List<TabPermissionResponseDTO>> processor(Long roleId) {
        List<PermissionDO> permissionList = permissionMapper.selectList(Wrappers.emptyWrapper());
        Map<String, List<PermissionDO>> collect = permissionList.stream()
                .collect(Collectors.groupingBy(PermissionDO::getAppId, Collectors.toList()));
        List<Long> permissionIds = rolePermissionSupport.listByRoleId(roleId).stream()
                .map(RolePermissionDO::getPermissionId)
                .collect(Collectors.toList());
        return ResultUtil.success(PermissionSupport.getTabPermissionResponseDTOS(collect, permissionIds));
    }
}
