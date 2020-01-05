package com.dmall.bms.service.impl.userrole;

import com.dmall.bms.api.dto.userrole.request.SaveUserRoleRequestDTO;
import com.dmall.bms.api.dto.userrole.request.UpdateUserRoleRequestDTO;
import com.dmall.bms.api.dto.userrole.request.ListUserRoleRequestDTO;
import com.dmall.bms.api.dto.userrole.request.PageUserRoleRequestDTO;
import com.dmall.bms.api.dto.userrole.common.CommonUserRoleResponseDTO;
import com.dmall.bms.api.service.UserRoleService;
import com.dmall.bms.service.impl.userrole.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 后台用户-角色服务实现
 * @author: created by hang.yu on 2020-01-05 18:36:38
 */
@RestController
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private SaveUserRoleHandler saveUserRoleHandler;

    @Autowired
    private DeleteUserRoleHandler deleteUserRoleHandler;

    @Autowired
    private UpdateUserRoleHandler updateUserRoleHandler;

    @Autowired
    private GetUserRoleHandler getUserRoleHandler;

    @Autowired
    private ListUserRoleHandler listUserRoleHandler;

    @Autowired
    private PageUserRoleHandler pageUserRoleHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveUserRoleRequestDTO requestDTO) {
        return saveUserRoleHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteUserRoleHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateUserRoleRequestDTO requestDTO) {
        return updateUserRoleHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonUserRoleResponseDTO> get(Long id) {
        return getUserRoleHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonUserRoleResponseDTO>> list(@RequestBody ListUserRoleRequestDTO requestDTO) {
        return listUserRoleHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<CommonUserRoleResponseDTO>> page(@RequestBody PageUserRoleRequestDTO requestDTO) {
        return pageUserRoleHandler.handler(requestDTO);
    }

}
