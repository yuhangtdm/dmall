package com.dmall.bms.service.impl.userpermission;

import com.dmall.bms.api.dto.userpermission.request.SaveUserPermissionRequestDTO;
import com.dmall.bms.api.dto.userpermission.request.UpdateUserPermissionRequestDTO;
import com.dmall.bms.api.dto.userpermission.request.ListUserPermissionRequestDTO;
import com.dmall.bms.api.dto.userpermission.request.PageUserPermissionRequestDTO;
import com.dmall.bms.api.dto.userpermission.common.CommonUserPermissionResponseDTO;
import com.dmall.bms.api.service.UserPermissionService;
import com.dmall.bms.service.impl.userpermission.handler.*;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @description: 后台用户和权限关系表 除角色中定义的权限以外的加减资源，加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限服务实现
 * @author: created by hang.yu on 2020-01-13 23:04:04
 */
@RestController
public class UserPermissionServiceImpl implements UserPermissionService {

    @Autowired
    private SaveUserPermissionHandler saveUserPermissionHandler;

    @Autowired
    private DeleteUserPermissionHandler deleteUserPermissionHandler;

    @Autowired
    private UpdateUserPermissionHandler updateUserPermissionHandler;

    @Autowired
    private GetUserPermissionHandler getUserPermissionHandler;

    @Autowired
    private ListUserPermissionHandler listUserPermissionHandler;

    @Autowired
    private PageUserPermissionHandler pageUserPermissionHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveUserPermissionRequestDTO requestDTO) {
        return saveUserPermissionHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteUserPermissionHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateUserPermissionRequestDTO requestDTO) {
        return updateUserPermissionHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonUserPermissionResponseDTO> get(Long id) {
        return getUserPermissionHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonUserPermissionResponseDTO>> list(@RequestBody ListUserPermissionRequestDTO requestDTO) {
        return listUserPermissionHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayUiPage<CommonUserPermissionResponseDTO>> page(@RequestBody PageUserPermissionRequestDTO requestDTO) {
        return pageUserPermissionHandler.handler(requestDTO);
    }

}
