package com.dmall.pms.business.service.brand.handler;

import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.brand.common.BrandCommonResponseDTO;
import com.dmall.pms.api.dto.brand.list.ListBrandRequestDTO;
import com.dmall.pms.business.service.brand.cache.BrandCacheService;
import com.dmall.pms.generator.dataobject.BrandDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询品牌列表处理器
 * @author: created by hang.yu on 2019/11/23 13:24
 */
@Component
public class ListBrandHandler extends AbstractCommonHandler<ListBrandRequestDTO, BrandDO, BrandCommonResponseDTO> {

    @Autowired
    private BrandCacheService brandCacheService;

    @Override
    protected BaseResult processor(ListBrandRequestDTO requestDTO) {
        return ResultUtil.success( brandCacheService.listBrand(requestDTO));
    }

}
