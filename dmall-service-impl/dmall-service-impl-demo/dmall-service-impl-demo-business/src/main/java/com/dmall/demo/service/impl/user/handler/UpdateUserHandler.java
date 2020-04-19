package com.dmall.demo.service.impl.user.handler;

import com.dmall.demo.api.dto.user.request.UpdateUserRequestDTO;
import com.dmall.demo.generator.dataobject.UserDO;
import com.dmall.demo.generator.mapper.UserMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改后台用户处理器
 * @author: created by hang.yu on 2020-04-19 21:22:50
 */
@Component
public class UpdateUserHandler extends AbstractCommonHandler<UpdateUserRequestDTO, UserDO, Long> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResult<Long> validate(UpdateUserRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateUserRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}