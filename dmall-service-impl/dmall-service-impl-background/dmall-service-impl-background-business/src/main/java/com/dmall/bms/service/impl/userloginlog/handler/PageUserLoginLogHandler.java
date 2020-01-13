package com.dmall.bms.service.impl.userloginlog.handler;

import com.dmall.bms.api.dto.userloginlog.common.CommonUserLoginLogResponseDTO;
import com.dmall.bms.api.dto.userloginlog.request.PageUserLoginLogRequestDTO;
import com.dmall.bms.generator.dataobject.UserLoginLogDO;
import com.dmall.bms.generator.mapper.UserLoginLogMapper;
import com.dmall.common.dto.LayUiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 后台用户登录日志分页处理器
 * @author: created by hang.yu on 2020-01-13 23:04:04
 */
@Component
public class PageUserLoginLogHandler extends AbstractCommonHandler<PageUserLoginLogRequestDTO, UserLoginLogDO, CommonUserLoginLogResponseDTO> {

    @Autowired
    private UserLoginLogMapper userLoginLogMapper;

    @Override
    public BaseResult<LayUiPage<CommonUserLoginLogResponseDTO>> validate(PageUserLoginLogRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayUiPage<CommonUserLoginLogResponseDTO>> processor(PageUserLoginLogRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
