package com.dmall.bms.service.impl.userloginlog;

import com.dmall.bms.api.dto.userloginlog.request.SaveUserLoginLogRequestDTO;
import com.dmall.bms.api.dto.userloginlog.request.UpdateUserLoginLogRequestDTO;
import com.dmall.bms.api.dto.userloginlog.request.ListUserLoginLogRequestDTO;
import com.dmall.bms.api.dto.userloginlog.request.PageUserLoginLogRequestDTO;
import com.dmall.bms.api.dto.userloginlog.common.CommonUserLoginLogResponseDTO;
import com.dmall.bms.api.service.UserLoginLogService;
import com.dmall.bms.service.impl.userloginlog.handler.*;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 后台用户登录日志服务实现
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@RestController
public class UserLoginLogServiceImpl implements UserLoginLogService {

    @Autowired
    private SaveUserLoginLogHandler saveUserLoginLogHandler;

    @Autowired
    private DeleteUserLoginLogHandler deleteUserLoginLogHandler;

    @Autowired
    private UpdateUserLoginLogHandler updateUserLoginLogHandler;

    @Autowired
    private GetUserLoginLogHandler getUserLoginLogHandler;

    @Autowired
    private ListUserLoginLogHandler listUserLoginLogHandler;

    @Autowired
    private PageUserLoginLogHandler pageUserLoginLogHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveUserLoginLogRequestDTO requestDTO) {
        return saveUserLoginLogHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteUserLoginLogHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateUserLoginLogRequestDTO requestDTO) {
        return updateUserLoginLogHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonUserLoginLogResponseDTO> get(Long id) {
        return getUserLoginLogHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonUserLoginLogResponseDTO>> list(@RequestBody ListUserLoginLogRequestDTO requestDTO) {
        return listUserLoginLogHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayUiPage<CommonUserLoginLogResponseDTO>> page(@RequestBody PageUserLoginLogRequestDTO requestDTO) {
        return pageUserLoginLogHandler.handler(requestDTO);
    }

}
