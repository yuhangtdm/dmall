package com.dmall.bms.service.impl.user.handler;

import com.dmall.bms.api.enums.BackGroundErrorEnum;
import com.dmall.bms.feign.AdminLoginFeign;
import com.dmall.bms.generator.dataobject.UserDO;
import com.dmall.bms.generator.mapper.UserMapper;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.model.exception.BusinessException;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 禁用后台用户处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class DeleteUserHandler extends AbstractCommonHandler<Long, UserDO, Long> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminLoginFeign adminLoginFeign;

    @Override
    public BaseResult<Long> processor(Long id) {
        // id存在
        UserDO userDO = userMapper.selectById(id);
        if (userDO == null) {
            return ResultUtil.fail(BackGroundErrorEnum.USER_NOT_EXIST);
        }
        if (YNEnum.N.getCode().equals(userDO.getStatus())) {
            return ResultUtil.fail(BackGroundErrorEnum.DELETE_ERROR);
        }
        userMapper.deleteById(id);
        // 清空登录
        BaseResult<Void> clearLoginResult = adminLoginFeign.clearLogin(userDO.getPhone());
        if (!clearLoginResult.getResult()) {
            throw new BusinessException(clearLoginResult.getCode(), clearLoginResult.getMsg());
        }
        return ResultUtil.success(id);
    }

}
