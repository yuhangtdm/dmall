package com.dmall.pms.service.impl.brand.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.util.ObjectUtil;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.brand.common.CommonBrandResponseDTO;
import com.dmall.pms.api.dto.brand.request.ListBrandRequestDTO;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.mapper.BrandMapper;
import com.dmall.pms.service.impl.brand.cache.BrandCacheService;
import com.dmall.pms.service.impl.brand.mapper.BrandCategoryMapper;
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
    private BrandMapper brandMapper;

    @Autowired
    private BrandCategoryMapper brandCategoryMapper;

    @Autowired
    private BrandCacheService brandCacheService;

    @Override
    public BaseResult<List<CommonBrandResponseDTO>> processor(ListBrandRequestDTO requestDTO) {
        List<BrandDO> brandDOS;
        if (ObjectUtil.allEmpty(requestDTO.getEnglishName(), requestDTO.getName(), requestDTO.getFirstLetter(), requestDTO.getCategoryId())) {
            brandDOS = brandCacheService.selectAll();
        } else {
            if (requestDTO.getCategoryId() != null) {
                brandDOS = brandCategoryMapper.selectBrand(requestDTO);
            } else {
                LambdaQueryWrapper<BrandDO> queryWrapper = Wrappers.<BrandDO>lambdaQuery()
                        .like(StrUtil.isNotBlank(requestDTO.getEnglishName()), BrandDO::getEnglishName, requestDTO.getEnglishName())
                        .like(StrUtil.isNotBlank(requestDTO.getName()), BrandDO::getName, requestDTO.getName())
                        .eq(StrUtil.isNotBlank(requestDTO.getFirstLetter()), BrandDO::getFirstLetter, requestDTO.getFirstLetter());
                brandDOS = brandMapper.selectList(queryWrapper);
            }

        }
        List<CommonBrandResponseDTO> list = brandDOS.stream()
                .filter(Objects::nonNull)
                .map(doo -> doConvertDto(doo, CommonBrandResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(list);
    }

}
