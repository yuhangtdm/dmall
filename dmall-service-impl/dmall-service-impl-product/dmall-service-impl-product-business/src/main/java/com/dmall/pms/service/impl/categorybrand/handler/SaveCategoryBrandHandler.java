package com.dmall.pms.service.impl.categorybrand.handler;

import com.dmall.pms.api.dto.categorybrand.request.SaveCategoryBrandRequestDTO;
import com.dmall.pms.service.impl.categorybrand.enums.CategoryBrandErrorEnum;
import com.dmall.pms.generator.dataobject.CategoryBrandDO;
import com.dmall.pms.generator.mapper.CategoryBrandMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增分类品牌关系处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class SaveCategoryBrandHandler extends AbstractCommonHandler<SaveCategoryBrandRequestDTO, CategoryBrandDO, Long> {

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Override
    public BaseResult<Long> validate(SaveCategoryBrandRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveCategoryBrandRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
