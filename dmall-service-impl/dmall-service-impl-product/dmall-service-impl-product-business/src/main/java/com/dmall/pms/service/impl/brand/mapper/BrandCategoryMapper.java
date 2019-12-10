package com.dmall.pms.service.impl.brand.mapper;

import com.dmall.pms.api.dto.brand.request.ListBrandRequestDTO;
import com.dmall.pms.generator.dataobject.BrandDO;

import java.util.List;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/10 21:20
 */
public interface BrandCategoryMapper {

    List<BrandDO> selectBrand(ListBrandRequestDTO requestDTO);
}
