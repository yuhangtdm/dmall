package com.dmall.pms.service.impl.attributetype.handler;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.attributetype.request.UpdateAttributeTypeRequestDTO;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.impl.attributetype.enums.AttributeTypeErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改属性分类处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class UpdateAttributeTypeHandler extends AbstractCommonHandler<UpdateAttributeTypeRequestDTO, AttributeTypeDO, Long> {

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Override
    public BaseResult validate(UpdateAttributeTypeRequestDTO requestDTO) {
        AttributeTypeDO attributeTypeDO = attributeTypeMapper.selectById(requestDTO.getId());
        if (attributeTypeDO == null) {
            return ResultUtil.fail(AttributeTypeErrorEnum.ATTRIBUTE_TYPE_NOT_EXIST);
        }
        // 名称唯一
        AttributeTypeDO nameAttributeType = attributeTypeMapper.selectOne(Wrappers.<AttributeTypeDO>lambdaQuery()
                .eq(AttributeTypeDO::getName, requestDTO.getName()));
        if (nameAttributeType != null && ObjectUtil.notEqual(nameAttributeType.getId(), requestDTO.getId())) {
            return ResultUtil.fail(AttributeTypeErrorEnum.SAVE_ATTRIBUTE_TYPE_ERROR);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateAttributeTypeRequestDTO requestDTO) {
        AttributeTypeDO attributeTypeDO = dtoConvertDo(requestDTO, AttributeTypeDO.class);
        attributeTypeCacheService.updateById(attributeTypeDO);
        return ResultUtil.success(attributeTypeDO.getId());
    }

}
