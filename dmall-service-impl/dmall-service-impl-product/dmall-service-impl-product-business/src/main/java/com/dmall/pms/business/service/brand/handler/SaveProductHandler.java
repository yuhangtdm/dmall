package com.dmall.pms.business.service.brand.handler;

import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.brand.save.SaveBrandRequestDTO;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: created by yuhang on 2019/11/19 23:27
 */
@Component
public class SaveProductHandler extends AbstractCommonHandler<SaveBrandRequestDTO,BrandDO> {

    @Autowired
    private BrandService brandService;

    /**
     * 新增品牌
     */
    public Long save(SaveBrandRequestDTO requestDTO) {
        BrandDO brandDO = dtoConvertDo(requestDTO, BrandDO.class);
        if (brandService.save(brandDO)){
            return brandDO.getId();
        }
        return -1L;
    }

    @Override
    public void validate(SaveBrandRequestDTO requestDTO) {

    }
}
