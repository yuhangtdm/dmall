package com.dmall.bms.service.impl.menu.handler;

import com.dmall.bms.api.enums.BackGroundErrorEnum;
import com.dmall.bms.generator.dataobject.MenuDO;
import com.dmall.bms.generator.mapper.MenuMapper;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 删除菜单处理器
 * @author: created by hang.yu on 2020-02-20 21:36:43
 */
@Component
public class DeleteMenuHandler extends AbstractCommonHandler<Long, MenuDO, Long> {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public BaseResult<Long> processor(Long id) {
        MenuDO menuDO = menuMapper.selectById(id);
        if (menuDO == null) {
            return ResultUtil.fail(BackGroundErrorEnum.MENU_NOT_EXIST);
        }
        menuMapper.deleteById(id);
        return ResultUtil.success(id);
    }

}
