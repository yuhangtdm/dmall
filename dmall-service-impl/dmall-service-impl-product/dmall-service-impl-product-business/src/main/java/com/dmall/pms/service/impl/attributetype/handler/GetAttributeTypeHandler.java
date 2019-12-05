package com.dmall.pms.service.impl.attributetype.handler;

import com.dmall.pms.api.dto.attributetype.common.CommonAttributeTypeResponseDTO;
import com.dmall.pms.service.impl.attributetype.enums.AttributeTypeErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询属性分类处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class GetAttributeTypeHandler extends AbstractCommonHandler<Long, AttributeTypeDO, CommonAttributeTypeResponseDTO> {

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Override
    public BaseResult<CommonAttributeTypeResponseDTO> processor(Long id) {
        AttributeTypeDO attributeTypeDO = attributeTypeMapper.selectById(id);
        if (attributeTypeDO == null){
            return ResultUtil.fail(AttributeTypeErrorEnum.ATTRIBUTETYPE_NOT_EXIST);
        }
        return ResultUtil.success(doConvertDto(attributeTypeDO, CommonAttributeTypeResponseDTO.class));
    }

}
