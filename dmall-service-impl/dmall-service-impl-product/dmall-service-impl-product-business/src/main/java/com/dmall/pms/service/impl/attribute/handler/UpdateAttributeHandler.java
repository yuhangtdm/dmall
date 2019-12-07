package com.dmall.pms.service.impl.attribute.handler;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.attribute.request.UpdateAttributeRequestDTO;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.mapper.AttributeMapper;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.impl.attribute.enums.AttributeErrorEnum;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改属性处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class UpdateAttributeHandler extends AbstractCommonHandler<UpdateAttributeRequestDTO, AttributeDO, Long> {

    @Autowired
    private AttributeMapper attributeMapper;

    @Autowired
    private AttributeCacheService attributeCacheService;

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Override
    public BaseResult<Long> validate(UpdateAttributeRequestDTO requestDTO) {
        // 查询分类
        AttributeDO attributeDO = attributeCacheService.selectById(requestDTO.getId());
        if (attributeDO == null){
            return ResultUtil.fail(AttributeErrorEnum.ATTRIBUTE_NOT_EXIST);
        }
        // 同一属性分类下名称必须相同
        AttributeDO nameAttributeDO = attributeMapper.selectOne(Wrappers.<AttributeDO>lambdaQuery()
                .eq(AttributeDO::getAttributeTypeId, attributeDO.getAttributeTypeId())
                .eq(AttributeDO::getName, requestDTO.getName()));
        // 属性分类下的属性名称必须唯一
        if (nameAttributeDO != null && ObjectUtil.notEqual(nameAttributeDO.getId(), requestDTO.getId())){
            return ResultUtil.fail(AttributeErrorEnum.ATTRIBUTE_NAME_UNIQUE);
        }


        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateAttributeRequestDTO requestDTO) {
        AttributeDO attributeDO = dtoConvertDo(requestDTO, AttributeDO.class);
        attributeCacheService.updateById(attributeDO);
        return ResultUtil.success();
    }

    @Override
    protected void customerConvertDo(AttributeDO result, UpdateAttributeRequestDTO requestDTO) {
        AttributeDO attributeDO = attributeCacheService.selectById(result.getId());
        AttributeTypeDO attributeTypeDO = attributeTypeCacheService.selectById(attributeDO.getAttributeTypeId());
        // 设置商品分类id 值可能为null
        result.setCategoryId(attributeTypeDO.getCategoryId());
        result.setCascadeCategoryId(attributeTypeDO.getCascadeCategoryId());
    }

}
