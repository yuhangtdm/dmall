package com.dmall.pms.service.impl.attribute.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.api.dto.attribute.request.SaveAttributeRequestDTO;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.impl.attribute.enums.AttributeErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.mapper.AttributeMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 新增属性处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class SaveAttributeHandler extends AbstractCommonHandler<SaveAttributeRequestDTO, AttributeDO, Long> {

    @Autowired
    private AttributeMapper attributeMapper;

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private AttributeCacheService attributeCacheService;

    @Override
    public BaseResult<Long> validate(SaveAttributeRequestDTO requestDTO) {
        AttributeDO attributeDO = attributeMapper.selectOne(Wrappers.<AttributeDO>lambdaQuery()
                .eq(AttributeDO::getAttributeTypeId, requestDTO.getAttributeTypeId())
                .eq(AttributeDO::getName, requestDTO.getName()));
        // 属性分类下的属性名称必须唯一
        if (attributeDO != null) {
            return ResultUtil.fail(AttributeErrorEnum.ATTRIBUTE_NAME_UNIQUE);
        }
        // 属性分类id存在
        AttributeTypeDO attributeTypeDO = attributeTypeCacheService.selectById(requestDTO.getAttributeTypeId());
        if (attributeTypeDO == null) {
            return ResultUtil.fail(AttributeErrorEnum.ATTRIBUTE_TYPE_EXIST);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveAttributeRequestDTO requestDTO) {
        AttributeDO attributeDO = dtoConvertDo(requestDTO, AttributeDO.class);
        attributeCacheService.insert(attributeDO);
        return ResultUtil.success(attributeDO.getId());
    }

    @Override
    protected void customerConvertDo(AttributeDO result, SaveAttributeRequestDTO requestDTO) {
        result.setInputList(CollUtil.join(requestDTO.getInputList(), StrUtil.COMMA));
        AttributeTypeDO attributeTypeDO = attributeTypeCacheService.selectById(requestDTO.getAttributeTypeId());
        // 设置商品分类id 值可能为null
//        result.setCategoryId(attributeTypeDO.getCategoryId());
//        result.setCascadeCategoryId(attributeTypeDO.getCascadeCategoryId());
    }
}
