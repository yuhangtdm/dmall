package com.dmall.bms.service.impl.userloginlog.handler;

import com.dmall.bms.api.dto.userloginlog.common.CommonUserLoginLogResponseDTO;
import com.dmall.bms.api.dto.userloginlog.request.ListUserLoginLogRequestDTO;
import com.dmall.bms.generator.dataobject.UserLoginLogDO;
import com.dmall.bms.generator.mapper.UserLoginLogMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 后台用户登录日志列表处理器
 * @author: created by hang.yu on 2020-01-13 23:04:04
 */
@Component
public class ListUserLoginLogHandler extends AbstractCommonHandler<ListUserLoginLogRequestDTO, UserLoginLogDO, CommonUserLoginLogResponseDTO> {

    @Autowired
    private UserLoginLogMapper userLoginLogMapper;

    @Override
    public BaseResult<List<CommonUserLoginLogResponseDTO>> validate(ListUserLoginLogRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonUserLoginLogResponseDTO>> processor(ListUserLoginLogRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
