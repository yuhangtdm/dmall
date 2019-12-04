package com.dmall.pms.service.impl.brand.handler;

import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.brand.common.CommonBrandResponseDTO;
import com.dmall.pms.api.dto.brand.request.ListBrandRequestDTO;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.service.impl.brand.cache.BrandCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description: 品牌列表处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class ListBrandHandler extends AbstractCommonHandler<ListBrandRequestDTO, BrandDO, CommonBrandResponseDTO> {

    @Autowired
    private BrandCacheService brandCacheService;

    @Override
    public BaseResult<List<CommonBrandResponseDTO>> processor(ListBrandRequestDTO requestDTO) {
        List<CommonBrandResponseDTO> list = brandCacheService.selectList(requestDTO).stream()
                .filter(Objects::nonNull)
                .map(doo -> doConvertDto(doo, CommonBrandResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(list);
    }

}
