package com.dmall.pms.service.impl.category.handler;

import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.category.common.CommonCategoryResponseDTO;
import com.dmall.pms.api.dto.category.request.ListCategoryRequestDTO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 商品分类列表处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class ListCategoryHandler extends AbstractCommonHandler<ListCategoryRequestDTO, CategoryDO, CommonCategoryResponseDTO> {

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Override
    public BaseResult<List<CommonCategoryResponseDTO>> processor(ListCategoryRequestDTO requestDTO) {
        List<CommonCategoryResponseDTO> list = categoryCacheService.selectList(requestDTO).stream()
                .map(doo -> doConvertDto(doo, CommonCategoryResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(list);
    }

}
