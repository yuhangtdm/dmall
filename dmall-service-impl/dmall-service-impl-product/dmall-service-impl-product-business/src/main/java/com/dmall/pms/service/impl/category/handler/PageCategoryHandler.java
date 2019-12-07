package com.dmall.pms.service.impl.category.handler;

import com.dmall.pms.api.dto.category.common.CommonCategoryResponseDTO;
import com.dmall.pms.api.dto.category.request.PageCategoryRequestDTO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.CategoryMapper;
import com.dmall.common.model.result.LayuiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 商品分类分页处理器
 * @author: created by hang.yu on 2019-12-07 20:44:53
 */
@Component
public class PageCategoryHandler extends AbstractCommonHandler<PageCategoryRequestDTO, CategoryDO, CommonCategoryResponseDTO> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public BaseResult<LayuiPage<CommonCategoryResponseDTO>> validate(PageCategoryRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<CommonCategoryResponseDTO>> processor(PageCategoryRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
