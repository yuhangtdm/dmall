package com.dmall.pms.service.impl.category.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.category.request.UpdateCategoryRequestDTO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改商品分类处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class UpdateCategoryHandler extends AbstractCommonHandler<UpdateCategoryRequestDTO, CategoryDO, Long> {

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult<Long> validate(UpdateCategoryRequestDTO requestDTO) {
        // 分类不存在
        pmsValidate.validateCategory(requestDTO.getId());
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateCategoryRequestDTO requestDTO) {
        CategoryDO categoryDO = dtoConvertDo(requestDTO, CategoryDO.class);
        categoryCacheService.updateById(categoryDO);
        return ResultUtil.success(requestDTO.getId());
    }

}
