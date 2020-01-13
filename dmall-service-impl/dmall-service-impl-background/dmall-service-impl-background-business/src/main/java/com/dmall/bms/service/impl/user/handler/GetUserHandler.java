package com.dmall.bms.service.impl.user.handler;

import com.dmall.bms.api.dto.user.common.CommonUserResponseDTO;
import com.dmall.bms.service.impl.user.enums.UserErrorEnum;
import com.dmall.bms.generator.dataobject.UserDO;
import com.dmall.bms.generator.mapper.UserMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询后台用户处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class GetUserHandler extends AbstractCommonHandler<Long, UserDO, CommonUserResponseDTO> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResult<CommonUserResponseDTO> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<CommonUserResponseDTO> processor(Long id) {
        return ResultUtil.success();
    }

}
