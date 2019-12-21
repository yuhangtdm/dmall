package com.dmall.pms.service.impl.attribute.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dmall.common.enums.base.EnumUtil;
import com.dmall.common.util.ObjectUtil;
import com.dmall.pms.api.dto.attribute.common.CommonAttributeResponseDTO;
import com.dmall.pms.api.dto.attributetype.enums.AttributeTypeEnum;
import com.dmall.pms.api.dto.attribute.enums.HandAddStatusEnum;
import com.dmall.pms.api.dto.attribute.enums.InputTypeEnum;
import com.dmall.pms.api.dto.attribute.request.ListAttributeRequestDTO;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.mapper.AttributeMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description: 属性列表处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class ListAttributeHandler extends AbstractCommonHandler<ListAttributeRequestDTO, AttributeDO, CommonAttributeResponseDTO> {

    @Autowired
    private AttributeMapper attributeMapper;

    @Autowired
    private AttributeCacheService attributeCacheService;

    @Override
    public BaseResult<List<CommonAttributeResponseDTO>> processor(ListAttributeRequestDTO requestDTO) {
        List<AttributeDO> attributeDOS;
        if (ObjectUtil.allEmpty(requestDTO.getAttributeTypeId(), requestDTO.getHandAddStatus(), requestDTO.getInputType(),
                requestDTO.getName())) {
            attributeDOS = attributeCacheService.selectAll();
        } else {
            LambdaQueryWrapper<AttributeDO> wrapper = Wrappers.<AttributeDO>lambdaQuery()
//                    .eq(ObjectUtil.isNotEmpty(requestDTO.getAttributeTypeId()), AttributeDO::getCascadeCategoryId, requestDTO.getAttributeTypeId())
                    .like(StrUtil.isNotBlank(requestDTO.getName()), AttributeDO::getName, requestDTO.getName())
                    .eq(ObjectUtil.isNotEmpty(requestDTO.getHandAddStatus()), AttributeDO::getHandAddStatus, requestDTO.getHandAddStatus())
                    .eq(ObjectUtil.isNotEmpty(requestDTO.getInputType()), AttributeDO::getInputType, requestDTO.getInputType());
            attributeDOS = attributeMapper.selectList(wrapper);
        }
        List<CommonAttributeResponseDTO> collect = attributeDOS.stream()
                .filter(Objects::nonNull)
                .map(attributeDO -> doConvertDto(attributeDO, CommonAttributeResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(collect);
    }

    @Override
    protected void customerConvertDto(CommonAttributeResponseDTO result, AttributeDO doo) {
        result.setInputType(EnumUtil.getKeyValueEnum(InputTypeEnum.class, doo.getInputType()));
        result.setHandAddStatus(EnumUtil.getKeyValueEnum(HandAddStatusEnum.class, doo.getHandAddStatus()));
    }

}
