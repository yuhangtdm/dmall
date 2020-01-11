package com.dmall.bms.service.impl.role.handler;

import com.dmall.bms.api.dto.role.common.CommonRoleResponseDTO;
import com.dmall.bms.api.dto.role.request.PageRoleRequestDTO;
import com.dmall.bms.generator.dataobject.RoleDO;
import com.dmall.bms.generator.mapper.RoleMapper;
import com.dmall.common.dto.LayUiPage;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 后台角色分页处理器
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Component
public class PageRoleHandler extends AbstractCommonHandler<PageRoleRequestDTO, RoleDO, CommonRoleResponseDTO> {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public BaseResult<LayUiPage<CommonRoleResponseDTO>> validate(PageRoleRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonRoleResponseDTO>> processor(PageRoleRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
