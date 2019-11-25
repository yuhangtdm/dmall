package com.dmall.pms.business.service.brand.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.cache.redis.mapcache.MapCacheable;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.brand.common.BrandCommonResponseDTO;
import com.dmall.pms.api.dto.brand.request.ListBrandRequestDTO;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.mapper.BrandMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 查询品牌列表处理器
 * @author: created by hang.yu on 2019/11/23 13:24
 */
@Component
public class ListBrandHandler extends AbstractCommonHandler<ListBrandRequestDTO, BrandDO, BrandCommonResponseDTO> {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    @MapCacheable
    public BaseResult processor(ListBrandRequestDTO requestDTO) {
        LambdaQueryWrapper<BrandDO> queryWrapper = Wrappers.<BrandDO>lambdaQuery()
                .like(StrUtil.isNotBlank(requestDTO.getEnglishName()), BrandDO::getEnglishName, requestDTO.getEnglishName())
                .like(StrUtil.isNotBlank(requestDTO.getName()), BrandDO::getName, requestDTO.getName())
                .eq(StrUtil.isNotBlank(requestDTO.getFirstLetter()), BrandDO::getFirstLetter, requestDTO.getFirstLetter());

        List<BrandCommonResponseDTO> list = brandMapper.selectList(queryWrapper).stream()
                .map(doo -> doConvertDto(doo, BrandCommonResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(list);
    }

}
