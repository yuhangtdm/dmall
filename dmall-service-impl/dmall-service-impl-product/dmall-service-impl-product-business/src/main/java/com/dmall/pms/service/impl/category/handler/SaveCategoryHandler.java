package com.dmall.pms.service.impl.category.handler;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.category.enums.LevelEnum;
import com.dmall.pms.api.dto.category.request.SaveCategoryRequestDTO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.CategoryMapper;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.impl.category.enums.CategoryErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增商品分类处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class SaveCategoryHandler extends AbstractCommonHandler<SaveCategoryRequestDTO, CategoryDO, Long> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Override
    public BaseResult<Long> validate(SaveCategoryRequestDTO requestDTO) {
        if (requestDTO.getParentId() != 0) {
            CategoryDO parentCategoryDO = categoryMapper.selectById(requestDTO.getParentId());
            // 上级id是否存在
            if (parentCategoryDO == null) {
                return ResultUtil.fail(CategoryErrorEnum.PARENT_CATEGORY_NOT_EXIST);
            }
            // 自己的分类级别要小于上级的
            if (requestDTO.getLevel() <= parentCategoryDO.getLevel()) {
                return ResultUtil.fail(CategoryErrorEnum.PARENT_LEVEL_ERROR);
            }
        }
        // 当ParentId=0时 level必须为1
        if (requestDTO.getParentId() == 0 && !LevelEnum.ONE.getCode().equals(requestDTO.getLevel())) {
            return ResultUtil.fail(CategoryErrorEnum.PARENT_LEVEL_ERROR);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveCategoryRequestDTO requestDTO) {
        CategoryDO categoryDO = dtoConvertDo(requestDTO, CategoryDO.class);
        categoryCacheService.insert(categoryDO);
        categoryCacheService.updateById(setPath(categoryDO));
        return ResultUtil.success(categoryDO.getId());
    }

    /**
     * 设置path
     */
    private CategoryDO setPath(CategoryDO result) {
        CategoryDO categoryDO = new CategoryDO();
        StringBuilder path = new StringBuilder();
        switch (result.getLevel()) {
            case 1: {
                path.append(StrUtil.DOT).append(result.getId()).append(StrUtil.DOT);
                break;
            }
            // 2,3级别一致
            default: {
                CategoryDO parentCategoryDO = categoryMapper.selectById(result.getParentId());
                path.append(parentCategoryDO.getPath()).append(result.getId()).append(StrUtil.DOT);
                break;
            }
        }
        categoryDO.setPath(path.toString());
        categoryDO.setId(result.getId());
        return categoryDO;
    }

}
