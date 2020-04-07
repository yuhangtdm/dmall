package com.dmall.pms.service.impl.category.handler;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.enums.YNEnum;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.pms.api.dto.category.common.CommonCategoryResponseDTO;
import com.dmall.pms.api.dto.category.enums.LevelEnum;
import com.dmall.pms.api.dto.category.request.ListCategoryRequestDTO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.CategoryMapper;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description: 商品分类列表处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class ListCategoryHandler extends AbstractCommonHandler<ListCategoryRequestDTO, CategoryDO, CommonCategoryResponseDTO> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Override
    public BaseResult<List<CommonCategoryResponseDTO>> processor(ListCategoryRequestDTO requestDTO) {
        List<CategoryDO> categoryDOList;
        if (com.dmall.common.util.ObjectUtil.allEmpty(requestDTO.getName(), requestDTO.getParentId(), requestDTO.getLevel())) {
            categoryDOList = categoryCacheService.selectAll();
        } else {
            LambdaQueryWrapper<CategoryDO> queryWrapper = Wrappers.<CategoryDO>lambdaQuery()
                    .like(StrUtil.isNotBlank(requestDTO.getName()), CategoryDO::getName, requestDTO.getName())
                    .eq(ObjectUtil.isNotNull(requestDTO.getParentId()), CategoryDO::getParentId, requestDTO.getParentId())
                    .eq(ObjectUtil.isNotNull(requestDTO.getLevel()), CategoryDO::getLevel, requestDTO.getLevel())
                    .orderByAsc(CategoryDO::getSort);
            categoryDOList = categoryMapper.selectList(queryWrapper);
        }
        List<CommonCategoryResponseDTO> list = categoryDOList.stream()
                .filter(Objects::nonNull)
                .map(doo -> doConvertDto(doo, CommonCategoryResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(list);
    }

    @Override
    protected void customerConvertDto(CommonCategoryResponseDTO result, CategoryDO doo) {
        result.setHotStatus(EnumUtil.getCodeDescEnum(YNEnum.class, doo.getHotStatus()));
        result.setLevel(EnumUtil.getCodeDescEnum(LevelEnum.class, doo.getLevel()));
        result.setNavStatus(EnumUtil.getCodeDescEnum(YNEnum.class, doo.getNavStatus()));
    }
}
