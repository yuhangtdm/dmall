package com.dmall.pms.service.impl.attributetype.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ObjectUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.attributetype.request.ListAttributeTypeRequestDTO;
import com.dmall.pms.api.dto.attributetype.response.AttributeTypeResponseDTO;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description: 属性类别列表处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class ListAttributeTypeHandler extends AbstractCommonHandler<ListAttributeTypeRequestDTO, AttributeTypeDO, AttributeTypeResponseDTO> {

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult validate(ListAttributeTypeRequestDTO requestDTO) {
        pmsValidate.validateBatchCategory(requestDTO.getCategoryIds());
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<AttributeTypeResponseDTO>> processor(ListAttributeTypeRequestDTO requestDTO) {
        List<AttributeTypeDO> attributeTypeDOS;
        if (ObjectUtil.allEmpty(requestDTO.getCategoryIds(), requestDTO.getShowName())) {
            attributeTypeDOS = attributeTypeCacheService.selectAll();
        } else {
            LambdaQueryWrapper<AttributeTypeDO> queryWrapper = Wrappers.<AttributeTypeDO>lambdaQuery()
                    .in(CollUtil.isNotEmpty(requestDTO.getCategoryIds()), AttributeTypeDO::getCategoryId, requestDTO.getCategoryIds())
                    .like(StrUtil.isNotBlank(requestDTO.getShowName()), AttributeTypeDO::getShowName, requestDTO.getShowName());
            attributeTypeDOS = attributeTypeMapper.selectList(queryWrapper);
        }
        List<AttributeTypeResponseDTO> list = attributeTypeDOS.stream()
                .filter(Objects::nonNull)
                .map(doo -> doConvertDto(doo, AttributeTypeResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(list);
    }

}
