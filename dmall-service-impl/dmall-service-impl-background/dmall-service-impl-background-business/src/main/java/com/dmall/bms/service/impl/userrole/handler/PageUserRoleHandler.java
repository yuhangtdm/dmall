package com.dmall.bms.service.impl.userrole.handler;

import com.dmall.bms.api.dto.userrole.common.CommonUserRoleResponseDTO;
import com.dmall.bms.api.dto.userrole.request.PageUserRoleRequestDTO;
import com.dmall.bms.generator.dataobject.UserRoleDO;
import com.dmall.bms.generator.mapper.UserRoleMapper;
import com.dmall.common.dto.LayUiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 后台用户-角色分页处理器
 * @author: created by hang.yu on 2020-01-13 23:04:04
 */
@Component
public class PageUserRoleHandler extends AbstractCommonHandler<PageUserRoleRequestDTO, UserRoleDO, CommonUserRoleResponseDTO> {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public BaseResult<LayUiPage<CommonUserRoleResponseDTO>> validate(PageUserRoleRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonUserRoleResponseDTO>> processor(PageUserRoleRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
