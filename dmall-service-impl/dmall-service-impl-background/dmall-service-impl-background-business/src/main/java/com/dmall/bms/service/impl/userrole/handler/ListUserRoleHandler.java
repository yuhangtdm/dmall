package com.dmall.bms.service.impl.userrole.handler;

import com.dmall.bms.api.dto.userrole.common.CommonUserRoleResponseDTO;
import com.dmall.bms.api.dto.userrole.request.ListUserRoleRequestDTO;
import com.dmall.bms.generator.dataobject.UserRoleDO;
import com.dmall.bms.generator.mapper.UserRoleMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 后台用户-角色列表处理器
 * @author: created by hang.yu on 2020-01-13 23:04:04
 */
@Component
public class ListUserRoleHandler extends AbstractCommonHandler<ListUserRoleRequestDTO, UserRoleDO, CommonUserRoleResponseDTO> {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public BaseResult<List<CommonUserRoleResponseDTO>> validate(ListUserRoleRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonUserRoleResponseDTO>> processor(ListUserRoleRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
