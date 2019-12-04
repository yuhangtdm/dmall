package com.dmall.pms.service.impl.category.handler;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.category.enums.LevelEnum;
import com.dmall.pms.api.dto.category.request.ListCategoryRequestDTO;
import com.dmall.pms.api.dto.category.response.ZTreeCategoryResponseDTO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.CategoryMapper;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.impl.category.enums.CategoryErrorEnum;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: ZTreeCategoryHandler
 * @author: created by hang.yu on 2019/11/24 18:36
 */
@Component
public class ZTreeCategoryHandler extends AbstractCommonHandler<Long, CategoryDO, ZTreeCategoryResponseDTO> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Override
    public BaseResult processor(Long parentId) {
        CategoryDO categoryDO = categoryMapper.selectById(parentId);
        if (parentId != 0 && categoryDO == null) {
            return ResultUtil.fail(CategoryErrorEnum.PARENT_CATEGORY_NOT_EXIST);
        }
        List<CategoryDO> categoryDOList;
        if (parentId == 0L) {
            categoryDOList = categoryCacheService.selectList(new ListCategoryRequestDTO());
        } else {
            categoryDOList = categoryMapper.selectList(Wrappers.<CategoryDO>lambdaQuery()
                    .like(CategoryDO::getPath, categoryDO.getPath()));
        }
        Map<Long, ZTreeCategoryResponseDTO> zTreeMap = categoryDOList.stream()
                .map(doo -> doConvertDto(doo, ZTreeCategoryResponseDTO.class))
                .collect(Collectors.toMap(ZTreeCategoryResponseDTO::getId, responseDTO -> responseDTO));

        if (parentId != 0L) {
            zTreeMap.get(parentId).setOpen(Boolean.TRUE);
        }

        return ResultUtil.success(tree(zTreeMap, parentId));
    }

    @Override
    protected void customerConvertDto(ZTreeCategoryResponseDTO result, CategoryDO doo) {
        result.setOpen(Boolean.FALSE);
        result.setIsParent(LevelEnum.ONE.getCode().equals(result.getLevel()) || LevelEnum.TWO.getCode().equals(result.getLevel()));
    }

    private List<ZTreeCategoryResponseDTO> tree(Map<Long, ZTreeCategoryResponseDTO> zTreeMap, Long parentId) {
        List<ZTreeCategoryResponseDTO> tree = Lists.newArrayList();

        // 添加parentId自身
        zTreeMap.forEach((k, v) -> {
            if (parentId == 0L && ObjectUtil.equal(v.getParentId(), parentId)) {
                tree.add(v);
            }
            if (ObjectUtil.equal(k, parentId)) {
                tree.add(v);
            }
        });

        // 遍历所有元素
        zTreeMap.forEach((k, v) -> {
            if (zTreeMap.get(v.getParentId()) != null) {
                zTreeMap.get(v.getParentId()).getChildren().add(v);
            }
        });

        return tree;
    }
}
