package com.dmall.pms.service.impl.attribute.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.pms.api.dto.attribute.enums.TypeEnum;
import com.dmall.pms.api.dto.attribute.request.UpdateAttributeRequestDTO;
import com.dmall.pms.api.enums.AttributeErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.CategoryAttributeDO;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.impl.attribute.validate.AttributeValidate;
import com.dmall.pms.service.impl.support.CategoryAttributeSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 修改属性处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class UpdateAttributeHandler extends AbstractCommonHandler<UpdateAttributeRequestDTO, AttributeDO, Long> {

    @Autowired
    private AttributeCacheService attributeCacheService;

    @Autowired
    private CategoryAttributeSupport categoryAttributeSupport;

    @Override
    public BaseResult validate(UpdateAttributeRequestDTO requestDTO) {
        // 查询属性
        AttributeDO attributeDO = attributeCacheService.selectById(requestDTO.getId());
        if (attributeDO == null) {
            return ResultUtil.fail(AttributeErrorEnum.ATTRIBUTE_NOT_EXIST);
        }
        // 公共属性不能修改为普通属性
        if (TypeEnum.PUBLIC.getCode().equals(attributeDO.getType()) && TypeEnum.NORMAL.getCode().equals(requestDTO.getType())) {
            List<CategoryAttributeDO> list = categoryAttributeSupport.listByAttributeId(requestDTO.getId());
            if (CollUtil.isNotEmpty(list) && list.size() > 1) {
                return ResultUtil.fail(AttributeErrorEnum.ATTRIBUTE_TYPE_INVALID);
            }
        }
        return AttributeValidate.validate(requestDTO.getInputType(), requestDTO.getInputList(), requestDTO.getHandAddStatus());

    }

    @Override
    public BaseResult<Long> processor(UpdateAttributeRequestDTO requestDTO) {
        AttributeDO attributeDO = attributeCacheService.selectById(requestDTO.getId());
        AttributeDO updateDO = dtoConvertDo(requestDTO, AttributeDO.class);
        updateDO.setName(StrUtil.format("{}_{}", attributeDO.getName().split("_")[0], requestDTO.getShowName()));
        attributeCacheService.updateById(updateDO);
        return ResultUtil.success();
    }

    @Override
    protected void customerConvertDo(AttributeDO result, UpdateAttributeRequestDTO requestDTO) {
        result.setInputList(CollUtil.join(requestDTO.getInputList(), StrUtil.COMMA));
    }

}
