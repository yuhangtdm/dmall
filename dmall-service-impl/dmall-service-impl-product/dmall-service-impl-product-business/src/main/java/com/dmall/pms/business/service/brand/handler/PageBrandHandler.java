package com.dmall.pms.business.service.brand.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.brand.request.BrandPageRequestDTO;
import com.dmall.pms.api.dto.brand.common.BrandCommonResponseDTO;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 分页处理器
 * @author: created by hang.yu on 2019/11/23 12:46
 */
@Component
public class PageBrandHandler extends AbstractCommonHandler<BrandPageRequestDTO, BrandDO, BrandCommonResponseDTO> {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public BaseResult processor(BrandPageRequestDTO requestDTO) {
        LambdaQueryWrapper<BrandDO> queryWrapper = Wrappers.<BrandDO>lambdaQuery()
                .like(StrUtil.isNotBlank(requestDTO.getName()), BrandDO::getName, requestDTO.getName())
                .like(StrUtil.isNotBlank(requestDTO.getName()), BrandDO::getEnglishName, requestDTO.getEnglishName())
                .eq(StrUtil.isNotBlank(requestDTO.getName()), BrandDO::getFirstLetter, requestDTO.getFirstLetter());

        Page<BrandDO> page = new Page<>(requestDTO.getCurrent(), requestDTO.getSize());
        IPage<BrandDO> brandDOIPage = brandMapper.selectPage(page, queryWrapper);
        List<BrandCommonResponseDTO> record = brandDOIPage.getRecords().stream()
                .map(doo -> doConvertDto(doo, BrandCommonResponseDTO.class))
                .collect(Collectors.toList());

        return ResultUtil.success(new LayuiPage(brandDOIPage.getTotal(), record));
    }

}
