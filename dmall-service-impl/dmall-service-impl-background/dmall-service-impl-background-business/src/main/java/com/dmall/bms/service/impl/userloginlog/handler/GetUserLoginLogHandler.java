package com.dmall.bms.service.impl.userloginlog.handler;

import com.dmall.bms.api.dto.userloginlog.common.CommonUserLoginLogResponseDTO;
import com.dmall.bms.generator.dataobject.UserLoginLogDO;
import com.dmall.bms.generator.mapper.UserLoginLogMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询后台用户登录日志处理器
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Component
public class GetUserLoginLogHandler extends AbstractCommonHandler<Long, UserLoginLogDO, CommonUserLoginLogResponseDTO> {

    @Autowired
    private UserLoginLogMapper userLoginLogMapper;

    @Override
    public BaseResult<CommonUserLoginLogResponseDTO> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<CommonUserLoginLogResponseDTO> processor(Long id) {
        return ResultUtil.success();
    }

}
