package com.dmall.bms.service.impl.permission.handler;

import com.dmall.bms.api.dto.permission.request.UpdatePermissionRequestDTO;
import com.dmall.bms.api.enums.PermissionErrorEnum;
import com.dmall.bms.generator.dataobject.PermissionDO;
import com.dmall.bms.generator.mapper.PermissionMapper;
import com.dmall.bms.service.impl.support.PermissionSupport;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改权限处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class UpdatePermissionHandler extends AbstractCommonHandler<UpdatePermissionRequestDTO, PermissionDO, Long> {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private PermissionSupport permissionSupport;

    @Override
    public BaseResult<Long> validate(UpdatePermissionRequestDTO requestDTO) {
        // id存在
        PermissionDO permissionDO = permissionMapper.selectById(requestDTO.getId());
        if (permissionDO == null){
            return ResultUtil.fail(PermissionErrorEnum.PERMISSION_NOT_EXIST);
        }
        // 权限码唯一
        PermissionDO byCode = permissionSupport.getByCode(requestDTO.getCode());
        if (byCode != null && !byCode.getId().equals(permissionDO.getId())) {
            return ResultUtil.fail(PermissionErrorEnum.CODE_EXIST);
        }
        // 请求方式+uri唯一
        PermissionDO byUriAndMethod = permissionSupport.getByUriAndMethod(requestDTO.getUri(), requestDTO.getMethod());
        if (byUriAndMethod != null && !byUriAndMethod.getId().equals(permissionDO.getId())){
            return ResultUtil.fail(PermissionErrorEnum.URI_METHOD_EXIST);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdatePermissionRequestDTO requestDTO) {
        PermissionDO permissionDO = dtoConvertDo(requestDTO, PermissionDO.class);
        permissionMapper.updateById(permissionDO);
        return ResultUtil.success(permissionDO.getId());
    }

}