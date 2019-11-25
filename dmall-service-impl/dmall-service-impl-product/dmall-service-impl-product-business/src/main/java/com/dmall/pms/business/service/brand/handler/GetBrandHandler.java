package com.dmall.pms.business.service.brand.handler;

import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.brand.common.BrandCommonResponseDTO;
import com.dmall.pms.business.service.brand.enums.BrandErrorEnum;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: created by hang.yu on 2019/11/23 16:39
 */
@Component
public class GetBrandHandler extends AbstractCommonHandler<Long, BrandDO, BrandCommonResponseDTO> {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public BaseResult processor(Long id) {
        BrandDO brandDO = brandMapper.selectById(id);
        if (brandDO == null){
            return ResultUtil.fail(BrandErrorEnum.BRAND_NOT_EXIST);
        }
        return ResultUtil.success(brandDO);
    }
}
