package com.dmall.pms.service.impl.attribute.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.enums.base.EnumUtil;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.util.ObjectUtil;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.attribute.common.CommonAttributeResponseDTO;
import com.dmall.pms.api.dto.attribute.enums.HandAddStatusEnum;
import com.dmall.pms.api.dto.attribute.enums.InputTypeEnum;
import com.dmall.pms.api.dto.attribute.enums.TypeEnum;
import com.dmall.pms.api.dto.attribute.request.ListAttributeRequestDTO;
import com.dmall.pms.api.dto.category.enums.LevelEnum;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.AttributeMapper;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.impl.attribute.enums.AttributeErrorEnum;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
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

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Override
    public BaseResult validate(ListAttributeRequestDTO requestDTO) {
        // 分类必须存在 且必须是一级分类
        if (requestDTO.getCategoryId() != null){
            CategoryDO categoryDO = categoryCacheService.selectById(requestDTO.getCategoryId());
            if (categoryDO == null){
                return ResultUtil.fail(AttributeErrorEnum.CATEGORY_NOT_EXIST);
            }
            if (!LevelEnum.ONE.getCode().equals(categoryDO.getLevel())){
                return ResultUtil.fail(AttributeErrorEnum.CATEGORY_NOT_INVALID);
            }
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonAttributeResponseDTO>> processor(ListAttributeRequestDTO requestDTO) {
        List<AttributeDO> attributeDOS;
        if (ObjectUtil.allEmpty(requestDTO.getCategoryId(), requestDTO.getShowName(), requestDTO.getType(),
                requestDTO.getHandAddStatus(), requestDTO.getInputType())) {
            attributeDOS = attributeCacheService.selectAll();
        } else {
            String categoryName = null;
            if (requestDTO.getCategoryId() != null) {
                CategoryDO categoryDO = categoryCacheService.selectById(requestDTO.getCategoryId());
                categoryName = categoryDO.getName();
            }
            LambdaQueryWrapper<AttributeDO> wrapper = Wrappers.<AttributeDO>lambdaQuery()
                    .likeRight(StrUtil.isNotBlank(categoryName), AttributeDO::getName, categoryName)
                    .like(StrUtil.isNotBlank(requestDTO.getShowName()), AttributeDO::getShowName, requestDTO.getShowName())
                    .eq(ObjectUtil.isNotEmpty(requestDTO.getType()), AttributeDO::getType, requestDTO.getType())
                    .eq(ObjectUtil.isNotEmpty(requestDTO.getInputType()), AttributeDO::getInputType, requestDTO.getInputType())
                    .eq(ObjectUtil.isNotEmpty(requestDTO.getHandAddStatus()), AttributeDO::getHandAddStatus, requestDTO.getHandAddStatus());
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
        result.setType(EnumUtil.getKeyValueEnum(TypeEnum.class, doo.getInputType()));
    }

}
