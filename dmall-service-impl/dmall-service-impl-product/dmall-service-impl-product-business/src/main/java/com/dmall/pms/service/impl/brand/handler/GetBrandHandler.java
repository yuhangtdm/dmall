package com.dmall.pms.service.impl.brand.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.brand.response.BrandResponseDTO;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.service.impl.brand.cache.BrandCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询品牌处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class GetBrandHandler extends AbstractCommonHandler<Long, BrandDO, BrandResponseDTO> {

    @Autowired
    private BrandCacheService brandCacheService;

    @Override
    public BaseResult<BrandResponseDTO> processor(Long id) {
        BrandDO brandDO = brandCacheService.selectById(id);
        if (brandDO == null) {
            return ResultUtil.fail(PmsErrorEnum.BRAND_NOT_EXIST);
        }
        return ResultUtil.success(doConvertDto(brandDO, BrandResponseDTO.class));
    }

}
