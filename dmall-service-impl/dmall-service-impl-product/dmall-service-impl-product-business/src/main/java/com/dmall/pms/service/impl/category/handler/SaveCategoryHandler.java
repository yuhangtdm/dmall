package com.dmall.pms.service.impl.category.handler;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.category.request.SaveCategoryRequestDTO;
import com.dmall.pms.api.enums.LevelEnum;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.CategoryMapper;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.validate.PmsValidate;
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

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult<Long> validate(SaveCategoryRequestDTO requestDTO) {
        if (requestDTO.getParentId() != 0) {
            CategoryDO parentCategoryDO = pmsValidate.validateCategory(requestDTO.getParentId());
            // 上级id是否存在
            if (parentCategoryDO == null) {
                return ResultUtil.fail(PmsErrorEnum.PARENT_CATEGORY_NOT_EXIST);
            }
            // 分类级别要小于上级
            if (requestDTO.getLevel() <= parentCategoryDO.getLevel()) {
                return ResultUtil.fail(PmsErrorEnum.PARENT_LEVEL_ERROR);
            }
        }
        // 当ParentId=0时 level必须为1
        if (requestDTO.getParentId() == 0 && !LevelEnum.ONE.getCode().equals(requestDTO.getLevel())) {
            return ResultUtil.fail(PmsErrorEnum.PARENT_LEVEL_ERROR);
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
        if (result.getLevel() == 1) {
            path.append(StrUtil.DOT).append(result.getId()).append(StrUtil.DOT);
            // 2,3级别一致
        } else {
            CategoryDO parentCategoryDO = categoryMapper.selectById(result.getParentId());
            path.append(parentCategoryDO.getPath()).append(result.getId()).append(StrUtil.DOT);
        }
        categoryDO.setPath(path.toString());
        categoryDO.setId(result.getId());
        return categoryDO;
    }

}
