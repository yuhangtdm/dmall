package com.dmall.pms.service.impl.category.handler;

import com.dmall.common.enums.base.EnumUtil;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.category.common.CommonCategoryResponseDTO;
import com.dmall.pms.api.dto.category.enums.LevelEnum;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
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
    private CategoryCacheService categoryCacheService;

    @Override
    public BaseResult<CommonCategoryResponseDTO> processor(Long id) {
        CategoryDO categoryDO = categoryCacheService.selectById(id);
        if (categoryDO == null){
            return ResultUtil.fail(CategoryErrorEnum.CATEGORY_NOT_EXIST);
        }
        return ResultUtil.success(doConvertDto(categoryDO,CommonCategoryResponseDTO.class));
    }

    @Override
    protected void customerConvertDto(CommonCategoryResponseDTO result, CategoryDO doo) {
        result.setLevel(EnumUtil.getKeyValueEnum(LevelEnum.class, doo.getLevel()));
    }

}
