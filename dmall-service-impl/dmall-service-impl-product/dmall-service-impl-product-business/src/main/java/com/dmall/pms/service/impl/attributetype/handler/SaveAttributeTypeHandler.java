package com.dmall.pms.service.impl.attributetype.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.api.dto.attributetype.request.SaveAttributeTypeRequestDTO;
import com.dmall.pms.service.impl.attributetype.cache.AttributeTypeCacheService;
import com.dmall.pms.service.impl.attributetype.enums.AttributeTypeErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增属性分类处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class SaveAttributeTypeHandler extends AbstractCommonHandler<SaveAttributeTypeRequestDTO, AttributeTypeDO, Long> {

    @Autowired
    private AttributeTypeCacheService attributeTypeCacheService;

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Override
    public BaseResult validate(SaveAttributeTypeRequestDTO requestDTO) {
        AttributeTypeDO attributeTypeDO = attributeTypeMapper.selectOne(Wrappers.<AttributeTypeDO>lambdaQuery()
                .eq(AttributeTypeDO::getName, requestDTO.getName()));
        // 名称唯一
        if (attributeTypeDO != null) {
            return ResultUtil.fail(AttributeTypeErrorEnum.SAVE_ATTRIBUTETYPE_ERROR);
        }
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveAttributeTypeRequestDTO requestDTO) {
        AttributeTypeDO attributeTypeDO = dtoConvertDo(requestDTO, AttributeTypeDO.class);
        attributeTypeCacheService.insert(attributeTypeDO);
        return ResultUtil.success(attributeTypeDO.getId());
    }

}
