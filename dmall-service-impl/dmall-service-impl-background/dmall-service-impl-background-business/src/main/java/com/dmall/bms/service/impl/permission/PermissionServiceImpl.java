package com.dmall.bms.service.impl.permission;

import com.dmall.bms.api.dto.permission.common.CommonPermissionResponseDTO;
import com.dmall.bms.api.dto.permission.request.ListPermissionRequestDTO;
import com.dmall.bms.api.dto.permission.request.PagePermissionRequestDTO;
import com.dmall.bms.api.dto.permission.request.SavePermissionRequestDTO;
import com.dmall.bms.api.dto.permission.request.UpdatePermissionRequestDTO;
import com.dmall.bms.api.dto.permission.response.PagePermissionResponseDTO;
import com.dmall.bms.api.service.PermissionService;
import com.dmall.bms.service.impl.permission.handler.*;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 权限服务实现
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@RestController
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private SavePermissionHandler savePermissionHandler;

    @Autowired
    private DeletePermissionHandler deletePermissionHandler;

    @Autowired
    private UpdatePermissionHandler updatePermissionHandler;

    @Autowired
    private GetPermissionHandler getPermissionHandler;

    @Autowired
    private ListPermissionHandler listPermissionHandler;

    @Autowired
    private PagePermissionHandler pagePermissionHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SavePermissionRequestDTO requestDTO) {
        return savePermissionHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deletePermissionHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdatePermissionRequestDTO requestDTO) {
        return updatePermissionHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonPermissionResponseDTO> get(Long id) {
        return getPermissionHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonPermissionResponseDTO>> list(@RequestBody ListPermissionRequestDTO requestDTO) {
        return listPermissionHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayUiPage<PagePermissionResponseDTO>> page(@RequestBody PagePermissionRequestDTO requestDTO) {
        return pagePermissionHandler.handler(requestDTO);
    }

}
