package com.dmall.bms.service.impl.user.handler;

import com.dmall.bms.api.dto.user.common.CommonUserResponseDTO;
import com.dmall.bms.api.dto.user.request.ListUserRequestDTO;
import com.dmall.bms.generator.dataobject.UserDO;
import com.dmall.bms.generator.mapper.UserMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 后台用户列表处理器
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Component
public class ListUserHandler extends AbstractCommonHandler<ListUserRequestDTO, UserDO, CommonUserResponseDTO> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResult<List<CommonUserResponseDTO>> validate(ListUserRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonUserResponseDTO>> processor(ListUserRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
