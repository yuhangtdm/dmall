package com.dmall.pms.service.impl.category.support;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.category.enums.LevelEnum;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.impl.category.enums.CategoryErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: CategorySupport
 * @author: created by hang.yu on 2019/12/7 21:44
 */
@Component
public class CategorySupport {

    @Autowired
    private CategoryCacheService categoryCacheService;

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
}
