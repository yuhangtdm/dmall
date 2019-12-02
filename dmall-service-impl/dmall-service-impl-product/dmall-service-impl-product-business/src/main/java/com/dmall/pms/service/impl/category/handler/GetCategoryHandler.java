package com.dmall.pms.service.impl.category.handler;

import com.dmall.pms.api.dto.category.common.CommonCategoryResponseDTO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.CategoryMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.service.impl.category.enums.CategoryErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询商品分类处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class GetCategoryHandler extends AbstractCommonHandler<Long, CategoryDO, CommonCategoryResponseDTO> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public BaseResult<CommonCategoryResponseDTO> processor(Long id) {
        CategoryDO categoryDO = categoryMapper.selectById(id);
        if (categoryDO == null){
            return ResultUtil.fail(CategoryErrorEnum.CATEGORY_NOT_EXIST);
        }
        return ResultUtil.success(doConvertDto(categoryDO,CommonCategoryResponseDTO.class));
    }

}
