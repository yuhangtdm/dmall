package com.dmall.pms.service.impl.brand.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.util.ResultUtil;
import com.dmall.pms.api.dto.brand.request.PageBrandRequestDTO;
import com.dmall.pms.api.dto.brand.response.BrandResponseDTO;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 品牌分页处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class PageBrandHandler extends AbstractCommonHandler<PageBrandRequestDTO, BrandDO, BrandResponseDTO> {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public BaseResult<ResponsePage<BrandResponseDTO>> processor(PageBrandRequestDTO requestDTO) {
        LambdaQueryWrapper<BrandDO> queryWrapper = Wrappers.<BrandDO>lambdaQuery()
            .like(StrUtil.isNotBlank(requestDTO.getName()), BrandDO::getName, requestDTO.getName())
            .like(StrUtil.isNotBlank(requestDTO.getEnglishName()), BrandDO::getEnglishName, requestDTO.getEnglishName())
            .eq(StrUtil.isNotBlank(requestDTO.getFirstLetter()), BrandDO::getFirstLetter, requestDTO.getFirstLetter());

        Page<BrandDO> page = new Page(requestDTO.getCurrent(), requestDTO.getSize());
        IPage<BrandDO> brandDOIPage = brandMapper.selectPage(page, queryWrapper);
        List<BrandResponseDTO> record = brandDOIPage.getRecords().stream()
            .map(doo -> doConvertDto(doo, BrandResponseDTO.class))
            .collect(Collectors.toList());

        return ResultUtil.success(new ResponsePage(brandDOIPage.getTotal(), record));
    }

}
