package com.dmall.demo.service.impl.user;

import com.dmall.common.dto.*;
import com.dmall.demo.api.dto.user.request.SaveUserRequestDTO;
import com.dmall.demo.api.dto.user.request.UpdateUserRequestDTO;
import com.dmall.demo.api.dto.user.request.ListUserRequestDTO;
import com.dmall.demo.api.dto.user.request.PageUserRequestDTO;
import com.dmall.demo.api.dto.user.response.UserResponseDTO;
import com.dmall.demo.api.service.UserService;
import com.dmall.demo.service.impl.user.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 后台用户服务实现
 * @author: created by hang.yu on 2020-04-19 21:30:17
 */
@RestController
public class UserServiceImpl implements UserService {

    @Autowired
    private SaveUserHandler saveUserHandler;

    @Autowired
    private DeleteUserHandler deleteUserHandler;

    @Autowired
    private UpdateUserHandler updateUserHandler;

    @Autowired
    private GetUserHandler getUserHandler;

    @Autowired
    private ListUserHandler listUserHandler;

    @Autowired
    private PageUserHandler pageUserHandler;

    @Override
    public BaseResult<Long> save(@RequestBody SaveUserRequestDTO requestDTO) {
        return saveUserHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteUserHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateUserRequestDTO requestDTO) {
        return updateUserHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<UserResponseDTO> get(Long id) {
        return getUserHandler.handler(id);
    }

    @Override
    public BaseResult<List<UserResponseDTO>> list(@RequestBody ListUserRequestDTO requestDTO) {
        return listUserHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<ResponsePage<UserResponseDTO>> page(@RequestBody PageUserRequestDTO requestDTO) {
        return pageUserHandler.handler(requestDTO);
    }

}
