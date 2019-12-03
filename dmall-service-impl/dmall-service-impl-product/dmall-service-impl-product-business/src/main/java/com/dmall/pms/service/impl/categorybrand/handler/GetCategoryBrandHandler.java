package com.dmall.pms.service.impl.categorybrand.handler;

import com.dmall.pms.api.dto.categorybrand.common.CommonCategoryBrandResponseDTO;
import com.dmall.pms.service.impl.categorybrand.enums.CategoryBrandErrorEnum;
import com.dmall.pms.generator.dataobject.CategoryBrandDO;
import com.dmall.pms.generator.mapper.CategoryBrandMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询分类品牌关系处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class GetCategoryBrandHandler extends AbstractCommonHandler<Long, CategoryBrandDO, CommonCategoryBrandResponseDTO> {

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Override
    public BaseResult<CommonCategoryBrandResponseDTO> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<CommonCategoryBrandResponseDTO> processor(Long id) {
        return ResultUtil.success();
    }

}
