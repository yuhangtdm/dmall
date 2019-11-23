package com.dmall.pms.business.service.brand.cache;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.BeanUtil;
import com.dmall.pms.api.dto.brand.common.BrandCommonResponseDTO;
import com.dmall.pms.api.dto.brand.list.ListBrandRequestDTO;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.mapper.BrandMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: created by hang.yu on 2019/11/23 20:08
 */
@Component
public class BrandCacheService {

    @Autowired
    private BrandMapper brandMapper;

    public List<BrandCommonResponseDTO> listBrand(ListBrandRequestDTO requestDTO){
        LambdaQueryWrapper<BrandDO> queryWrapper = Wrappers.<BrandDO>lambdaQuery()
                .like(StringUtils.isNotBlank(requestDTO.getEnglishName()), BrandDO::getEnglishName, requestDTO.getEnglishName())
                .like(StringUtils.isNotBlank(requestDTO.getName()), BrandDO::getName, requestDTO.getName())
                .eq(StringUtils.isNotBlank(requestDTO.getFirstLetter()), BrandDO::getFirstLetter, requestDTO.getFirstLetter());

        List<BrandCommonResponseDTO> list = brandMapper.selectList(queryWrapper).stream()
                .map(doo -> BeanUtil.copyProperties(doo,BrandCommonResponseDTO.class))
                .collect(Collectors.toList());
        return list;
    }

}
