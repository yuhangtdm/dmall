package com.dmall.pms.service.impl.attribute.handler;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.attribute.request.SaveAttributeRequestDTO;
import com.dmall.pms.api.enums.HandAddStatusEnum;
import com.dmall.pms.api.enums.InputTypeEnum;
import com.dmall.pms.api.enums.LevelEnum;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
import com.dmall.pms.service.support.AttributeSupport;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增属性处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class SaveAttributeHandler extends AbstractCommonHandler<SaveAttributeRequestDTO, AttributeDO, Long> {

    @Autowired
    private CategoryCacheService categoryCacheService;

    @Autowired
    private AttributeCacheService attributeCacheService;

    @Autowired
    private PmsValidate pmsValidate;

    @Autowired
    private AttributeSupport attributeSupport;

    @Override
    public BaseResult<Long> validate(SaveAttributeRequestDTO requestDTO) {
        AttributeDO attributeDO = attributeSupport.getByShowName(requestDTO.getCategoryId(), requestDTO.getShowName());
        if (attributeDO == null) {
            return ResultUtil.fail(PmsErrorEnum.ATTRIBUTE_NAME_EXIST);
        }
        // 分类id必须存在
        CategoryDO categoryDO = pmsValidate.validateCategory(requestDTO.getCategoryId());
        // 必须为1级分类
        if (!LevelEnum.ONE.getCode().equals(categoryDO.getLevel())) {
            return ResultUtil.fail(PmsErrorEnum.CATEGORY_NOT_INVALID);
        }
        return pmsValidate.attributeValidate(requestDTO.getInputType(), requestDTO.getInputList(), requestDTO.getHandAddStatus());
    }

    @Override
    public BaseResult<Long> processor(SaveAttributeRequestDTO requestDTO) {
        AttributeDO attributeDO = dtoConvertDo(requestDTO, AttributeDO.class);
        attributeCacheService.insert(attributeDO);
        return ResultUtil.success(attributeDO.getId());
    }

    @Override
    protected void customerConvertDo(AttributeDO result, SaveAttributeRequestDTO requestDTO) {
        CategoryDO categoryDO = categoryCacheService.selectById(requestDTO.getCategoryId());
        result.setName(StrUtil.format("{}_{}", categoryDO.getName(), requestDTO.getShowName()));
        // 手工录入
        if (InputTypeEnum.HANDLE.getCode().equals(requestDTO.getInputType())) {
            result.setHandAddStatus(HandAddStatusEnum.Y.getCode());
            result.setInputList(null);
        }
        result.setCategoryId(categoryDO.getId());
    }
}
