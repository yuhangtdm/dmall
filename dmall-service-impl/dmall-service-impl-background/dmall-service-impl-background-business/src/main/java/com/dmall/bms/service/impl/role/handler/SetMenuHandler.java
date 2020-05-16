package com.dmall.bms.service.impl.role.handler;

import cn.hutool.core.collection.CollUtil;
import com.dmall.bms.api.enums.BackGroundErrorEnum;
import com.dmall.bms.generator.dataobject.MenuDO;
import com.dmall.bms.generator.dataobject.RoleDO;
import com.dmall.bms.generator.dataobject.RoleMenuDO;
import com.dmall.bms.generator.mapper.MenuMapper;
import com.dmall.bms.generator.mapper.RoleMapper;
import com.dmall.bms.generator.mapper.RoleMenuMapper;
import com.dmall.bms.service.support.RoleMenuSupport;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.CheckedDTO;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: SetMenuHandler
 * @author: created by hang.yu on 2020/2/20 21:52
 */
@Component
public class SetMenuHandler extends AbstractCommonHandler<CheckedDTO, RoleMenuDO, Long> {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuSupport roleMenuSupport;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public BaseResult<Long> validate(CheckedDTO requestDTO) {
        // 角色必须存在
        RoleDO roleDO = roleMapper.selectById(requestDTO.getId());
        if (roleDO == null) {
            return ResultUtil.fail(BackGroundErrorEnum.ROLE_NOT_EXIST);
        }
        // 菜单必须存在
        List<MenuDO> menuList = menuMapper.selectBatchIds(requestDTO.getRelateIds());
        if (menuList.size() != requestDTO.getRelateIds().size()) {
            return ResultUtil.fail(BackGroundErrorEnum.MENU_NOT_EXIST);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(CheckedDTO requestDTO) {
        List<RoleMenuDO> listByRoleId = roleMenuSupport.listByRoleId(requestDTO.getId());
        Set<Long> menuIds = requestDTO.getRelateIds();
        // 角色列表为空 只是新增
        if (CollUtil.isEmpty(listByRoleId)) {
            for (Long menuId : menuIds) {
                RoleMenuDO roleMenuDO = new RoleMenuDO()
                        .setRoleId(requestDTO.getId())
                        .setMenuId(menuId);
                roleMenuMapper.insert(roleMenuDO);
            }
        } else {
            // 先删后增
            List<Long> oldMenuIds = listByRoleId.stream().map(RoleMenuDO::getMenuId).collect(Collectors.toList());
            // 新增的集合
            List<Long> insertMenuIds = menuIds.stream()
                    .filter(menuId -> !oldMenuIds.contains(menuId))
                    .collect(Collectors.toList());
            // 删除的集合
            List<Long> deleteMenuIds = oldMenuIds.stream()
                    .filter(menuId -> !menuIds.contains(menuId))
                    .collect(Collectors.toList());
            if (CollUtil.isNotEmpty(deleteMenuIds)) {
                roleMenuSupport.delete(requestDTO.getId(), deleteMenuIds);
            }
            if (CollUtil.isNotEmpty(insertMenuIds)) {
                for (Long menuId : insertMenuIds) {
                    RoleMenuDO roleMenuDO = new RoleMenuDO()
                            .setRoleId(requestDTO.getId())
                            .setMenuId(menuId);
                    roleMenuMapper.insert(roleMenuDO);
                }
            }
        }

        return ResultUtil.success(requestDTO.getId());
    }
}
