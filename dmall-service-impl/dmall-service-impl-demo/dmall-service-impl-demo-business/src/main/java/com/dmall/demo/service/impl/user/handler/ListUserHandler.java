package com.dmall.demo.service.impl.user.handler;

import com.dmall.demo.api.dto.user.response.UserResponseDTO;
import com.dmall.demo.api.dto.user.request.ListUserRequestDTO;
import com.dmall.demo.generator.dataobject.UserDO;
import com.dmall.demo.generator.mapper.UserMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 后台用户列表处理器
 * @author: created by hang.yu on 2020-04-19 21:22:50
 */
@Component
public class ListUserHandler extends AbstractCommonHandler<ListUserRequestDTO, UserDO, UserResponseDTO> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResult<List<UserResponseDTO>> validate(ListUserRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<UserResponseDTO>> processor(ListUserRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
