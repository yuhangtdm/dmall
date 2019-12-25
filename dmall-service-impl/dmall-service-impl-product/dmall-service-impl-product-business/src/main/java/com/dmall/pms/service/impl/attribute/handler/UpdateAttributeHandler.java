package com.dmall.pms.service.impl.attribute.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.attribute.enums.TypeEnum;
import com.dmall.pms.api.dto.attribute.request.UpdateAttributeRequestDTO;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.impl.attribute.enums.AttributeErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改属性处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class UpdateAttributeHandler extends AbstractCommonHandler<UpdateAttributeRequestDTO, AttributeDO, Long> {

    @Autowired
    private AttributeCacheService attributeCacheService;

    @Override
    public BaseResult<Long> validate(UpdateAttributeRequestDTO requestDTO) {
        // 查询属性
        AttributeDO attributeDO = attributeCacheService.selectById(requestDTO.getId());
        if (attributeDO == null) {
            return ResultUtil.fail(AttributeErrorEnum.ATTRIBUTE_NOT_EXIST);
        }

        // 公共属性不能修改为普通属性
        if (TypeEnum.PUBLIC.getCode().equals(attributeDO.getType()) && TypeEnum.NORMAL.getCode().equals(requestDTO.getType())) {
            return ResultUtil.fail(AttributeErrorEnum.ATTRIBUTE_TYPE_INVALID);
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
        result.setInputList(CollUtil.join(requestDTO.getInputList(), StrUtil.COMMA));
    }

}
