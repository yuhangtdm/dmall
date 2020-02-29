package com.dmall.pms.service.impl.attribute.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.pms.api.dto.attribute.request.SaveAttributeRequestDTO;
import com.dmall.pms.api.dto.category.enums.LevelEnum;
import com.dmall.pms.api.enums.AttributeErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import com.dmall.pms.service.impl.attribute.validate.AttributeValidate;
import com.dmall.pms.service.impl.category.cache.CategoryCacheService;
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

    @Override
    public BaseResult<Long> validate(SaveAttributeRequestDTO requestDTO) {
        // 分类id必须存在
        CategoryDO categoryDO = categoryCacheService.selectById(requestDTO.getCategoryId());
        if (categoryDO == null) {
            return ResultUtil.fail(AttributeErrorEnum.CATEGORY_NOT_EXIST);
        }

        // 必须为1级分类
        if (!LevelEnum.ONE.getCode().equals(categoryDO.getLevel())) {
            return ResultUtil.fail(AttributeErrorEnum.CATEGORY_NOT_INVALID);
        }
        return AttributeValidate.validate(requestDTO.getInputType(), requestDTO.getInputList(), requestDTO.getHandAddStatus());
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
        CategoryDO categoryDO = categoryCacheService.selectById(requestDTO.getCategoryId());
        result.setName(StrUtil.format("{}_{}", categoryDO.getName(), requestDTO.getShowName()));
    }
}
