package com.dmall.bms.service.impl.userloginlog.handler;

import com.dmall.bms.api.dto.userloginlog.common.CommonUserLoginLogResponseDTO;
import com.dmall.bms.api.dto.userloginlog.request.PageUserLoginLogRequestDTO;
import com.dmall.bms.generator.dataobject.UserLoginLogDO;
import com.dmall.bms.generator.mapper.UserLoginLogMapper;
import com.dmall.common.model.result.LayuiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 后台用户登录日志分页处理器
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Component
public class PageUserLoginLogHandler extends AbstractCommonHandler<PageUserLoginLogRequestDTO, UserLoginLogDO, CommonUserLoginLogResponseDTO> {

    @Autowired
    private UserLoginLogMapper userLoginLogMapper;

    @Override
    public BaseResult<LayuiPage<CommonUserLoginLogResponseDTO>> validate(PageUserLoginLogRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<CommonUserLoginLogResponseDTO>> processor(PageUserLoginLogRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
