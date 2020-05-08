package com.dmall.bms.service.impl.user;

import com.dmall.bms.api.dto.user.request.*;
import com.dmall.bms.api.dto.user.response.UserResponseDTO;
import com.dmall.bms.api.service.UserService;
import com.dmall.bms.service.impl.user.handler.*;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.dto.UploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * @description: 后台用户服务实现
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@RestController
public class UserServiceImpl implements UserService {

    @Autowired
    private SaveUserHandler saveUserHandler;

    @Autowired
    private DisableUserHandler disableUserHandler;

    @Autowired
    private EnableUserHandler enableUserHandler;

    @Autowired
    private UpdateUserHandler updateUserHandler;

    @Autowired
    private GetUserHandler getUserHandler;

    @Autowired
    private PageUserHandler pageUserHandler;

    @Autowired
    private UploadIconHandler uploadIconHandler;

    @Autowired
    private SetRolesHandler setRolesHandler;

    @Autowired
    private UserSetPermissionsHandler userSetPermissionsHandler;

    @Autowired
    private UpdatePasswordHandler updatePasswordHandler;

    @Autowired
    private ResetPasswordUserHandler resetPasswordUserHandler;

    @Override
    public BaseResult<Long> save(@RequestBody SaveUserRequestDTO requestDTO) {
        return saveUserHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> disable(Long id) {
        return disableUserHandler.handler(id);
    }

    @Override
    public BaseResult<Long> enable(Long id) {
        return enableUserHandler.handler(id);
    }

    @Override
    public BaseResult<Long> resetPassword(Long id) {
        return resetPasswordUserHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateUserRequestDTO requestDTO) {
        return updateUserHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> updatePassword(@Valid UpdatePasswordRequestDTO requestDTO) {
        return updatePasswordHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<UserResponseDTO> get(Long id) {
        return getUserHandler.handler(id);
    }

    @Override
    public BaseResult<ResponsePage<UserResponseDTO>> page(@RequestBody PageUserRequestDTO requestDTO) {
        return pageUserHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<UploadResult> uploadIcon(MultipartFile file) {
        return uploadIconHandler.handler(file);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> setRoles(@RequestBody SetRolesRequestDTO requestDTO) {
        return setRolesHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> setPermissions(@RequestBody SetPermissionsExtRequestDTO requestDTO) {
        return userSetPermissionsHandler.handler(requestDTO);
    }


}
