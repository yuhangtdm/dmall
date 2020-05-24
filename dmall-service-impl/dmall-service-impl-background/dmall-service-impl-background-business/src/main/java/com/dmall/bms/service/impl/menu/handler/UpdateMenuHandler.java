package com.dmall.bms.service.impl.menu.handler;

import com.dmall.bms.api.dto.menu.request.UpdateMenuRequestDTO;
import com.dmall.bms.api.enums.BackGroundErrorEnum;
import com.dmall.bms.generator.dataobject.MenuDO;
import com.dmall.bms.generator.mapper.MenuMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改菜单表 处理器
 * @author: created by hang.yu on 2020-02-20 21:36:43
 */
@Component
public class UpdateMenuHandler extends AbstractCommonHandler<UpdateMenuRequestDTO, MenuDO, Long> {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public BaseResult<Long> validate(UpdateMenuRequestDTO requestDTO) {
        MenuDO menuDO = menuMapper.selectById(requestDTO.getId());
        if (menuDO == null) {
            return ResultUtil.fail(BackGroundErrorEnum.MENU_NOT_EXIST);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateMenuRequestDTO requestDTO) {
        MenuDO menuDO = dtoConvertDo(requestDTO, MenuDO.class);
        menuMapper.updateById(menuDO);
        return ResultUtil.success(menuDO.getId());
    }

}