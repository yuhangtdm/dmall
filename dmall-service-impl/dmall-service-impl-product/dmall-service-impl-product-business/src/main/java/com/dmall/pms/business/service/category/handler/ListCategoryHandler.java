package com.dmall.pms.business.service.category.handler;

import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.cache.redis.mapcache.MapCacheable;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.category.common.CategoryResponseDTO;
import com.dmall.pms.api.dto.category.request.ListCategoryRequestDTO;
import com.dmall.pms.business.service.category.cache.CategoryCacheService;
import com.dmall.pms.generator.dataobject.CategoryDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: ListCategoryHandler
 * @author: created by hang.yu on 2019/11/24 14:27
 */
@Component
public class ListCategoryHandler extends AbstractCommonHandler<ListCategoryRequestDTO, CategoryDO, CategoryResponseDTO> {

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Override
    @MapCacheable(key = "category")
    public BaseResult<List<CategoryResponseDTO>> processor(ListCategoryRequestDTO requestDTO) {
        List<CategoryResponseDTO> collect = categoryCacheService.list(requestDTO).stream()
                .map(doo -> doConvertDto(doo, CategoryResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(collect);
    }
}
