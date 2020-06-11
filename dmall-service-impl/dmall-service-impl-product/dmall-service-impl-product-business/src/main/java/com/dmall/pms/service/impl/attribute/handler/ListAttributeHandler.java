package com.dmall.pms.service.impl.attribute.handler;

import cn.hutool.core.collection.CollUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ObjectUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.attribute.request.ListAttributeRequestDTO;
import com.dmall.pms.api.dto.attribute.response.AttributeResponseDTO;
import com.dmall.pms.api.enums.LevelEnum;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.impl.attribute.mapper.AttributeListMapper;
import com.dmall.pms.service.validate.PmsValidate;
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
public class ListAttributeHandler
    extends AbstractCommonHandler<ListAttributeRequestDTO, AttributeDO, AttributeResponseDTO> {

    @Autowired
    private AttributeCacheService attributeCacheService;

    @Autowired
    private PmsValidate pmsValidate;

    @Autowired
    private AttributeListMapper attributeListMapper;

    @Override
    public BaseResult validate(ListAttributeRequestDTO requestDTO) {
        // 分类必须存在 且必须是一级分类
        if (requestDTO.getCategoryId() != null) {
            CategoryDO categoryDO = pmsValidate.validateCategory(requestDTO.getCategoryId());
            if (!LevelEnum.ONE.getCode().equals(categoryDO.getLevel())) {
                return ResultUtil.fail(PmsErrorEnum.CATEGORY_NOT_INVALID);
            }
        }
        // 校验三级分类
        if (CollUtil.isNotEmpty(requestDTO.getThreeCategoryIds())) {
            pmsValidate.validateBatchCategory(requestDTO.getThreeCategoryIds());
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<AttributeResponseDTO>> processor(ListAttributeRequestDTO requestDTO) {
        List<AttributeDO> attributeDOS;
        if (ObjectUtil.allEmpty(requestDTO.getCategoryId(), requestDTO.getShowName(), requestDTO.getHandAddStatus(),
            requestDTO.getInputType(), requestDTO.getThreeCategoryIds())) {
            attributeDOS = attributeCacheService.selectAll();
        } else {
            attributeDOS = attributeListMapper.listAttribute(requestDTO);
        }
        List<AttributeResponseDTO> collect = attributeDOS.stream()
            .filter(Objects::nonNull)
            .map(attributeDO -> doConvertDto(attributeDO, AttributeResponseDTO.class))
            .collect(Collectors.toList());
        return ResultUtil.success(collect);
    }

}
