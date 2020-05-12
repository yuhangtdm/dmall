package com.dmall.bms.service.impl.menu.handler;

import cn.hutool.core.util.StrUtil;
import com.dmall.bms.api.enums.BackGroundErrorEnum;
import com.dmall.bms.api.enums.MenuTypeEnum;
import com.dmall.bms.api.dto.menu.request.SaveMenuRequestDTO;
import com.dmall.bms.generator.dataobject.MenuDO;
import com.dmall.bms.generator.mapper.MenuMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增菜单表 处理器
 * @author: created by hang.yu on 2020-02-20 21:36:43
 */
@Component
public class SaveMenuHandler extends AbstractCommonHandler<SaveMenuRequestDTO, MenuDO, Long> {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public BaseResult<Long> validate(SaveMenuRequestDTO requestDTO) {
        // 上级id不为空时 必须存在 且必须是目录
        if (requestDTO.getParentId() != null) {
            MenuDO menuDO = menuMapper.selectById(requestDTO.getParentId());
            if (menuDO == null) {
                return ResultUtil.fail(BackGroundErrorEnum.PARENT_NOT_EXIST);
            }
            if (!MenuTypeEnum.CATALOG.getCode().equals(menuDO.getType())) {
                return ResultUtil.fail(BackGroundErrorEnum.PARENT_NOT_CATALOG);
            }
        }
        // 菜单的路径不能为空
        if (MenuTypeEnum.MENU.getCode().equals(requestDTO.getType())) {
            if (StrUtil.isBlank(requestDTO.getUrl())) {
                return ResultUtil.fail(BackGroundErrorEnum.URL_BLANK);
            }
        } else {
            if (StrUtil.isBlank(requestDTO.getOpen())) {
                return ResultUtil.fail(BackGroundErrorEnum.OPEN_NOT_EMPTY);
            }
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveMenuRequestDTO requestDTO) {
        MenuDO menuDO = dtoConvertDo(requestDTO, MenuDO.class);
        if (menuDO.getParentId() == null) {
            menuDO.setParentId(0L);
        }
        if (MenuTypeEnum.MENU.getCode().equals(requestDTO.getType())) {
            menuDO.setOpen(null);
        } else {
            menuDO.setUrl(null);
        }

        menuMapper.insert(menuDO);
        return ResultUtil.success(menuDO.getId());
    }

}
