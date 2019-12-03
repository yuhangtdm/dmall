package com.dmall.pms.service.impl.categorybrand.handler;

import com.dmall.pms.api.dto.categorybrand.common.CommonCategoryBrandResponseDTO;
import com.dmall.pms.api.dto.categorybrand.request.PageCategoryBrandRequestDTO;
import com.dmall.pms.generator.dataobject.CategoryBrandDO;
import com.dmall.pms.generator.mapper.CategoryBrandMapper;
import com.dmall.common.model.result.LayuiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 分类品牌关系分页处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class PageCategoryBrandHandler extends AbstractCommonHandler<PageCategoryBrandRequestDTO, CategoryBrandDO, CommonCategoryBrandResponseDTO> {

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Override
    public BaseResult<LayuiPage<CommonCategoryBrandResponseDTO>> validate(PageCategoryBrandRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<CommonCategoryBrandResponseDTO>> processor(PageCategoryBrandRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
