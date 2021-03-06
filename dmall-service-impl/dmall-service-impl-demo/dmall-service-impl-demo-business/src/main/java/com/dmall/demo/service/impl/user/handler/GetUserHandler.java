package com.dmall.demo.service.impl.user.handler;

import com.dmall.demo.api.dto.user.response.UserResponseDTO;
import com.dmall.demo.generator.dataobject.UserDO;
import com.dmall.demo.generator.mapper.UserMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询后台用户处理器
 * @author: created by hang.yu on 2020-04-19 21:30:17
 */
@Component
public class GetUserHandler extends AbstractCommonHandler<Long, UserDO, UserResponseDTO> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResult<UserResponseDTO> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<UserResponseDTO> processor(Long id) {
        return ResultUtil.success();
    }

}
