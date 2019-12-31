package com.dmall.pms.service.impl.brand.handler;

import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.brand.common.CommonBrandResponseDTO;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.service.impl.brand.cache.BrandCacheService;
import com.dmall.pms.service.impl.brand.enums.BrandErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询品牌处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class GetBrandHandler extends AbstractCommonHandler<Long, BrandDO, CommonBrandResponseDTO> {

    @Autowired
    private BrandCacheService brandCacheService;

    @Override
    public BaseResult<CommonBrandResponseDTO> processor(Long id) {
        BrandDO brandDO = brandCacheService.selectById(id);
        if (brandDO == null) {
            return ResultUtil.fail(BrandErrorEnum.BRAND_NOT_EXIST);
        }
        return ResultUtil.success(doConvertDto(brandDO, CommonBrandResponseDTO.class));
    }

}
