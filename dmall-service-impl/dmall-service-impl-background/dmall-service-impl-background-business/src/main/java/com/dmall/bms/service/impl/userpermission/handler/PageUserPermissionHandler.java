package com.dmall.bms.service.impl.userpermission.handler;

import com.dmall.bms.api.dto.userpermission.common.CommonUserPermissionResponseDTO;
import com.dmall.bms.api.dto.userpermission.request.PageUserPermissionRequestDTO;
import com.dmall.bms.generator.dataobject.UserPermissionDO;
import com.dmall.bms.generator.mapper.UserPermissionMapper;
import com.dmall.common.dto.LayUiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限分页处理器
 * @author: created by hang.yu on 2020-01-13 23:04:04
 */
@Component
public class PageUserPermissionHandler extends AbstractCommonHandler<PageUserPermissionRequestDTO, UserPermissionDO, CommonUserPermissionResponseDTO> {

    @Autowired
    private UserPermissionMapper userPermissionMapper;

    @Override
    public BaseResult<LayUiPage<CommonUserPermissionResponseDTO>> validate(PageUserPermissionRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonUserPermissionResponseDTO>> processor(PageUserPermissionRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
