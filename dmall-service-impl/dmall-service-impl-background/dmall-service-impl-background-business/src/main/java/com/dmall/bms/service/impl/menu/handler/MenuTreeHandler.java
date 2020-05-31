package com.dmall.bms.service.impl.menu.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.bms.api.enums.MenuTypeEnum;
import com.dmall.bms.generator.dataobject.MenuDO;
import com.dmall.bms.generator.mapper.MenuMapper;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.dtree.DTreeResponseDTO;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.model.DTreeUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: MenuTreeHandler
 * @author: created by hang.yu on 2020/2/22 15:25
 */
@Component
public class MenuTreeHandler extends AbstractCommonHandler<Void, MenuDO, DTreeResponseDTO> {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public BaseResult<List<DTreeResponseDTO>> processor(Void aVoid) {
        // 查询所有菜单
        List<DTreeResponseDTO> allMenus = menuMapper.selectList(Wrappers.<MenuDO>lambdaQuery()
                .orderByAsc(MenuDO::getSort)).stream()
                .map(this::buildDTreeResponse)
                .collect(Collectors.toList());
        return ResultUtil.success(DTreeUtil.build(allMenus));
    }

    /**
     * 构建树数据
     */
    private DTreeResponseDTO buildDTreeResponse(MenuDO menuDO) {
        DTreeResponseDTO responseDTO = new DTreeResponseDTO();
        responseDTO.setId(menuDO.getId());
        responseDTO.setParentId(menuDO.getParentId());
        responseDTO.setTitle(menuDO.getName());
        // 默认全部展开
        responseDTO.setSpread(YNEnum.Y.getCode().equals(menuDO.getOpen()));
        responseDTO.setParent(MenuTypeEnum.CATALOG.getCode().equals(menuDO.getType()));
        // 默认全部可用
        responseDTO.setDisabled(false);
        Map<String, Object> map = Maps.newHashMap();
        map.put("url", menuDO.getUrl());
        map.put("icon", menuDO.getIcon());
        map.put("target", menuDO.getTarget());
        map.put("sort", menuDO.getSort());
        map.put("type", menuDO.getType());
        responseDTO.setBasicData(map);
        responseDTO.setChildren(Lists.newArrayList());
        return responseDTO;
    }
}
