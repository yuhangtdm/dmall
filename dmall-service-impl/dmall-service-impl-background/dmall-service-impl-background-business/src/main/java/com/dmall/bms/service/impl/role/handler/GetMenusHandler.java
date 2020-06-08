package com.dmall.bms.service.impl.role.handler;

import com.dmall.bms.generator.dataobject.RoleMenuDO;
import com.dmall.bms.service.support.RoleMenuSupport;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: GetMenusHandler
 * @author: created by hang.yu on 2020/5/16 11:04
 */
@Component
public class GetMenusHandler extends AbstractCommonHandler<Long, RoleMenuDO, List<String>> {

    @Autowired
    private RoleMenuSupport roleMenuSupport;

    @Override
    public BaseResult<List<String>> processor(Long roleId) {
        List<String> menuIds = roleMenuSupport.listByRoleId(roleId).stream()
            .map(RoleMenuDO::getMenuId).map(String::valueOf).collect(Collectors.toList());
        return ResultUtil.success(menuIds);
    }
}
