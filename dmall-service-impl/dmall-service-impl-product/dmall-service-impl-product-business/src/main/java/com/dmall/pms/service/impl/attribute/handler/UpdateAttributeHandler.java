package com.dmall.pms.service.impl.attribute.handler;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.attribute.request.UpdateAttributeRequestDTO;
import com.dmall.pms.api.enums.HandAddStatusEnum;
import com.dmall.pms.api.enums.InputTypeEnum;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.support.AttributeSupport;
import com.dmall.pms.service.support.CategoryAttributeSupport;
import com.dmall.pms.service.validate.PmsValidate;
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

    @Autowired
    private CategoryAttributeSupport categoryAttributeSupport;

    @Autowired
    private PmsValidate pmsValidate;

    @Autowired
    private AttributeSupport attributeSupport;

    @Override
    public BaseResult validate(UpdateAttributeRequestDTO requestDTO) {
        // 查询属性
        AttributeDO attributeDO = pmsValidate.validateAttribute(requestDTO.getId());
        // 校验展示名称唯一
        AttributeDO attributeDO1 =
            attributeSupport.getByShowName(attributeDO.getCategoryId(), requestDTO.getShowName());
        if (attributeDO1 != null && !attributeDO1.getId().equals(attributeDO.getId())) {
            return ResultUtil.fail(PmsErrorEnum.ATTRIBUTE_NAME_EXIST);
        }
        return pmsValidate.attributeValidate(requestDTO.getInputType(), requestDTO.getInputList(),
            requestDTO.getHandAddStatus());

    }

    @Override
    public BaseResult<Long> processor(UpdateAttributeRequestDTO requestDTO) {
        AttributeDO attributeDO = attributeCacheService.selectById(requestDTO.getId());
        AttributeDO updateDO = dtoConvertDo(requestDTO, AttributeDO.class);
        // 修改名称
        updateDO.setName(StrUtil.format("{}_{}", attributeDO.getName().split(StrUtil.UNDERLINE)[0],
            requestDTO.getShowName()));
        attributeCacheService.updateById(updateDO);
        return ResultUtil.success();
    }

    @Override
    protected void customerConvertDo(AttributeDO result, UpdateAttributeRequestDTO requestDTO) {
        // 手工录入
        if (InputTypeEnum.HANDLE.getCode().equals(requestDTO.getInputType())) {
            result.setHandAddStatus(HandAddStatusEnum.Y.getCode());
            result.setInputList(null);
        }
    }

}
