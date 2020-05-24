package com.dmall.pms.service.impl.attribute.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.attribute.response.AttributeResponseDTO;
import com.dmall.pms.api.enums.HandAddStatusEnum;
import com.dmall.pms.api.enums.InputTypeEnum;
import com.dmall.pms.api.enums.TypeEnum;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询属性处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class GetAttributeHandler extends AbstractCommonHandler<Long, AttributeDO, AttributeResponseDTO> {

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult<AttributeResponseDTO> processor(Long id) {
        AttributeDO attributeDO = pmsValidate.validateAttribute(id);
        return ResultUtil.success(doConvertDto(attributeDO, AttributeResponseDTO.class));
    }

}
