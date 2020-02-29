package com.dmall.bms.service.impl.user.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.bms.api.dto.user.common.CommonUserResponseDTO;
import com.dmall.bms.api.dto.user.request.PageUserRequestDTO;
import com.dmall.bms.generator.dataobject.UserDO;
import com.dmall.bms.generator.mapper.UserMapper;
import com.dmall.common.dto.ResponsePage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 后台用户分页处理器
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Component
public class PageUserHandler extends AbstractCommonHandler<PageUserRequestDTO, UserDO, CommonUserResponseDTO> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResult<ResponsePage<CommonUserResponseDTO>> processor(PageUserRequestDTO requestDTO) {
        IPage<UserDO> page = new Page<>(requestDTO.getCurrent(), requestDTO.getSize());
        LambdaQueryWrapper<UserDO> like = Wrappers.<UserDO>lambdaQuery()
                .like(StrUtil.isNotBlank(requestDTO.getUserName()), UserDO::getUserName, requestDTO.getUserName())
                .like(StrUtil.isNotBlank(requestDTO.getNickName()), UserDO::getNickName, requestDTO.getNickName())
                .like(StrUtil.isNotBlank(requestDTO.getRealName()), UserDO::getRealName, requestDTO.getRealName())
                .like(StrUtil.isNotBlank(requestDTO.getPhone()), UserDO::getPhone, requestDTO.getPhone());
        page = userMapper.selectPage(page, like);
        List<CommonUserResponseDTO> collect = page.getRecords().stream()
                .map(userDO -> doConvertDto(userDO, CommonUserResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(new ResponsePage<>(page.getTotal(), collect));
    }

}
