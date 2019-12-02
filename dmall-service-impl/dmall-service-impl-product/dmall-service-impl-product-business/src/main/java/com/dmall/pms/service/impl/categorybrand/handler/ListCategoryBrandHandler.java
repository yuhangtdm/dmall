package com.dmall.pms.service.impl.categorybrand.handler;

import com.dmall.pms.api.dto.categorybrand.common.CommonCategoryBrandResponseDTO;
import com.dmall.pms.api.dto.categorybrand.request.ListCategoryBrandRequestDTO;
import com.dmall.pms.service.impl.categorybrand.enums.CategoryBrandErrorEnum;
import com.dmall.pms.generator.dataobject.CategoryBrandDO;
import com.dmall.pms.generator.mapper.CategoryBrandMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 分类品牌关系列表处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class ListCategoryBrandHandler extends AbstractCommonHandler<ListCategoryBrandRequestDTO, CategoryBrandDO, CommonCategoryBrandResponseDTO> {

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Override
    public BaseResult<List<CommonCategoryBrandResponseDTO>> validate(ListCategoryBrandRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonCategoryBrandResponseDTO>> processor(ListCategoryBrandRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
