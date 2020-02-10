package com.dmall.bms.service.impl.user.handler;

import com.dmall.bms.service.impl.user.enums.UserErrorEnum;
import com.dmall.bms.generator.dataobject.UserDO;
import com.dmall.bms.generator.mapper.UserMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 删除后台用户处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class DeleteUserHandler extends AbstractCommonHandler<Long, UserDO, Long> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResult<Long> validate(Long id) {
        // id存在
        UserDO userDO = userMapper.selectById(id);
        if (userDO == null) {
            return ResultUtil.fail(UserErrorEnum.USER_NOT_EXIST);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        userMapper.deleteById(id);
        return ResultUtil.success(id);
    }

}
