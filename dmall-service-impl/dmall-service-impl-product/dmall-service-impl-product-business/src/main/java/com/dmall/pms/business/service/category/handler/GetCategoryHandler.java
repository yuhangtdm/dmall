package com.dmall.pms.business.service.category.handler;

import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.business.service.category.enums.CategoryErrorEnum;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: GetCategoryHandler
 * @author: created by hang.yu on 2019/11/24 14:27
 */
@Component
public class GetCategoryHandler extends AbstractCommonHandler<Long, CategoryDO, Long> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public BaseResult validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult processor(Long id) {
        CategoryDO categoryDO = categoryMapper.selectById(id);
        if (categoryDO == null){
            return ResultUtil.fail(CategoryErrorEnum.CATEGORY_NOT_EXIST);
        }
        return ResultUtil.success(categoryDO);
    }
}
