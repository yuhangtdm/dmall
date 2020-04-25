package com.dmall.pms.service.impl.category.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.category.response.CategoryResponseDTO;
import com.dmall.pms.api.enums.LevelEnum;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询商品分类处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class GetCategoryHandler extends AbstractCommonHandler<Long, CategoryDO, CategoryResponseDTO> {

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult<CategoryResponseDTO> processor(Long id) {
        CategoryDO categoryDO = pmsValidate.validateCategory(id);
        return ResultUtil.success(doConvertDto(categoryDO, CategoryResponseDTO.class));
    }

    @Override
    protected void customerConvertDto(CategoryResponseDTO result, CategoryDO doo) {
        result.setLevel(EnumUtil.getCodeDescEnum(LevelEnum.class, doo.getLevel()));
        result.setHotStatus(EnumUtil.getCodeDescEnum(YNEnum.class, doo.getHotStatus()));
        result.setNavStatus(EnumUtil.getCodeDescEnum(YNEnum.class, doo.getNavStatus()));
    }

}
