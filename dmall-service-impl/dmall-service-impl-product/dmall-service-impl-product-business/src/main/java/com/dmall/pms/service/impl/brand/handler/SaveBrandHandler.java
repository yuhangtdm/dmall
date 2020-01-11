package com.dmall.pms.service.impl.brand.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.pms.api.dto.brand.request.SaveBrandRequestDTO;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.mapper.BrandMapper;
import com.dmall.pms.service.impl.brand.cache.BrandCacheService;
import com.dmall.pms.service.impl.brand.enums.BrandErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增品牌处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class SaveBrandHandler extends AbstractCommonHandler<SaveBrandRequestDTO, BrandDO, Long> {

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private BrandCacheService brandCacheService;

    @Override
    public BaseResult<Long> validate(SaveBrandRequestDTO requestDTO) {
        BrandDO brandDO = brandMapper.selectOne(Wrappers.<BrandDO>lambdaQuery().eq(BrandDO::getName, requestDTO.getName()));
        // 名称唯一
        if (brandDO != null) {
            return ResultUtil.fail(BrandErrorEnum.BRAND_NAME_UNIQUE);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveBrandRequestDTO requestDTO) {
        BrandDO brandDO = dtoConvertDo(requestDTO, BrandDO.class);
        brandCacheService.insert(brandDO);
        return ResultUtil.success(brandDO.getId());
    }

}
