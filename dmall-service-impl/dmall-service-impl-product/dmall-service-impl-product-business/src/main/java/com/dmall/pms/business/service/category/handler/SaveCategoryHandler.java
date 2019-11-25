package com.dmall.pms.business.service.category.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.category.enums.LevelEnum;
import com.dmall.pms.api.dto.category.request.SaveCategoryRequestDTO;
import com.dmall.pms.business.service.category.enums.CategoryErrorEnum;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.CategoryMapper;
import com.dmall.pms.generator.service.ICategoryBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: SaveCategoryHandler
 * @author: created by hang.yu on 2019/11/24 14:24
 */
@Component
public class SaveCategoryHandler extends AbstractCommonHandler<SaveCategoryRequestDTO, CategoryDO, Long> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public BaseResult validate(SaveCategoryRequestDTO requestDTO) {
        // 分类名称唯一
        CategoryDO categoryDO = categoryMapper.selectOne(Wrappers.<CategoryDO>lambdaQuery()
                .eq(CategoryDO::getName, requestDTO.getName()));
        if (categoryDO != null){
            return ResultUtil.fail(CategoryErrorEnum.CATEGORY_NAME_UNIQUE);
        }
        CategoryDO parentCategoryDO = categoryMapper.selectById(requestDTO.getParentId());
        if (requestDTO.getParentId() != 0 ){
            // 上级id是否存在分类
            if (parentCategoryDO == null){
                return ResultUtil.fail(CategoryErrorEnum.PARENT_CATEGORY_NOT_EXIST);
            }
            // 自己的分类级别要小于上级的
            if (requestDTO.getLevel() <= parentCategoryDO.getLevel()){
                return ResultUtil.fail(CategoryErrorEnum.PARENT_LEVEL_ERROR);
            }
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult processor(SaveCategoryRequestDTO requestDTO) {
        CategoryDO categoryDO = dtoConvertDo(requestDTO, CategoryDO.class);
        if (categoryMapper.insert(categoryDO) != 1){
            return ResultUtil.fail(CategoryErrorEnum.SAVE_CATEGORY_ERROR);
        }
        categoryMapper.updateById(setPath(categoryDO));
        return ResultUtil.success(categoryDO.getId());
    }

    @Override
    protected void customerConvertDo(CategoryDO result, SaveCategoryRequestDTO requestDTO) {
        // 初始值都是0
        result.setBrandCount(0);
        result.setAttributeCategoryCount(0);
        result.setAttributeCount(0);
    }

    /**
     * 设置path
     */
    private CategoryDO setPath(CategoryDO result) {
        CategoryDO categoryDO = new CategoryDO();
        StringBuilder path = new StringBuilder();
        switch (result.getLevel()){
            case 1:{
                path.append(StrUtil.DOT).append(result.getId()).append(StrUtil.DOT);
                break;
            }
            case 2:{
                CategoryDO parentCategoryDO = categoryMapper.selectById(result.getParentId());
                path.append(parentCategoryDO.getId()).append(result.getId()).append(StrUtil.DOT);
                break;
            }
            case 3:{
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
