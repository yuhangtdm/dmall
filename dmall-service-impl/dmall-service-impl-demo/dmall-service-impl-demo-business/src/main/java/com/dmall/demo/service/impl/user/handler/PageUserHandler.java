package com.dmall.demo.service.impl.user.handler;

import com.dmall.demo.api.dto.user.response.UserResponseDTO;
import com.dmall.demo.api.dto.user.request.PageUserRequestDTO;
import com.dmall.demo.generator.dataobject.UserDO;
import com.dmall.demo.generator.mapper.UserMapper;
import com.dmall.common.dto.ResponsePage;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 后台用户分页处理器
 * @author: created by hang.yu on 2020-04-19 21:22:50
 */
@Component
public class PageUserHandler extends AbstractCommonHandler<PageUserRequestDTO, UserDO, UserResponseDTO> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResult<ResponsePage<UserResponseDTO>> validate(PageUserRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<ResponsePage<UserResponseDTO>> processor(PageUserRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
