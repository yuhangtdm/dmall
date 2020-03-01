package com.dmall.pms.service.impl.support;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.pms.api.dto.category.enums.LevelEnum;
import com.dmall.pms.api.enums.CategoryErrorEnum;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.CategoryMapper;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: CategorySupport
 * @author: created by hang.yu on 2019/12/7 21:44
 */
@Component
public class CategorySupport {

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 获取级联的分类名称
     */
    public String getCascadeCategoryName(Long categoryId) {
        CategoryDO categoryDO = categoryCacheService.selectById(categoryId);
        StringBuilder sb = new StringBuilder();
        StrUtil.splitTrim(categoryDO.getPath(), CharUtil.DOT).stream().map(Long::valueOf).forEach(id -> {
            sb.append(categoryCacheService.selectById(id).getName()).append(StrUtil.SLASH);
        });
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    /**
     * 获取子级下所有的三级分类
     */
    public List<Long> getSubLevelIds(Long categoryId) {
        CategoryDO categoryDO = categoryCacheService.selectById(categoryId);
        if (LevelEnum.THREE.getCode().equals(categoryDO.getLevel())) {
            return Lists.newArrayList(categoryDO.getId());
        }
        List<CategoryDO> categoryDOList = categoryMapper.selectList(Wrappers.<CategoryDO>lambdaQuery()
                .like(CategoryDO::getPath, categoryDO.getPath()));
        return categoryDOList.stream().filter(category -> LevelEnum.THREE.getCode().equals(category.getLevel()))
                .map(CategoryDO::getId).collect(Collectors.toList());
    }

    public BaseResult validate(Long categoryId) {
        CategoryDO categoryDO = categoryCacheService.selectById(categoryId);
        // 分类id必须存在
        if (categoryDO == null) {
            return ResultUtil.fail(CategoryErrorEnum.CATEGORY_NOT_EXIST);
        }
        // 分类级别必须是3级
        if (!LevelEnum.THREE.getCode().equals(categoryDO.getLevel())) {
            return ResultUtil.fail(CategoryErrorEnum.PARENT_LEVEL_ERROR);
        }
        return ResultUtil.success();
    }

    /**
     * 根据父级id查询子分类列表
     */
    public List<CategoryDO> listByParentId(Long parentId) {
        return categoryMapper.selectList(Wrappers.<CategoryDO>lambdaQuery().eq(CategoryDO::getParentId, parentId));
    }

}
