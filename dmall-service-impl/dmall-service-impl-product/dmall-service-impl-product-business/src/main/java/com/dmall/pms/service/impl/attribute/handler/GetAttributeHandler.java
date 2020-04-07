package com.dmall.pms.service.impl.attribute.handler;

import com.dmall.common.util.EnumUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.pms.api.dto.attribute.common.CommonAttributeResponseDTO;
import com.dmall.pms.api.dto.attribute.enums.HandAddStatusEnum;
import com.dmall.pms.api.dto.attribute.enums.InputTypeEnum;
import com.dmall.pms.api.dto.attribute.enums.TypeEnum;
import com.dmall.pms.api.enums.AttributeErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.service.impl.attribute.cache.AttributeCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询属性处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class GetAttributeHandler extends AbstractCommonHandler<Long, AttributeDO, CommonAttributeResponseDTO> {

    @Autowired
    private AttributeCacheService attributeCacheService;

    @Override
    public BaseResult<CommonAttributeResponseDTO> processor(Long id) {
        AttributeDO attributeDO = attributeCacheService.selectById(id);
        if (attributeDO == null) {
            return ResultUtil.fail(AttributeErrorEnum.ATTRIBUTE_NOT_EXIST);
        }
        return ResultUtil.success(doConvertDto(attributeDO, CommonAttributeResponseDTO.class));
    }

    @Override
    protected void customerConvertDto(CommonAttributeResponseDTO result, AttributeDO doo) {
        result.setType(EnumUtil.getCodeDescEnum(TypeEnum.class, doo.getType()));
        result.setInputType(EnumUtil.getCodeDescEnum(InputTypeEnum.class, doo.getInputType()));
        result.setHandAddStatus(EnumUtil.getCodeDescEnum(HandAddStatusEnum.class, doo.getHandAddStatus()));
    }
}
