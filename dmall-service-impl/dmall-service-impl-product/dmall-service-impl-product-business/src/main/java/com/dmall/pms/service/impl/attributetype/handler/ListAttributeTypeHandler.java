package com.dmall.pms.service.impl.attributetype.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.attributetype.request.ListAttributeTypeRequestDTO;
import com.dmall.pms.api.dto.attributetype.response.ListAttributeTypeResponseDTO;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import com.dmall.pms.service.impl.attributetype.wrapper.LambdaQueryWrapperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 属性分类列表处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class ListAttributeTypeHandler extends AbstractCommonHandler<ListAttributeTypeRequestDTO, AttributeTypeDO, ListAttributeTypeResponseDTO> {

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Override
    public BaseResult<List<ListAttributeTypeResponseDTO>> processor(ListAttributeTypeRequestDTO requestDTO) {
        LambdaQueryWrapper<AttributeTypeDO> queryWrapper = LambdaQueryWrapperBuilder
                .queryWrapper(requestDTO.getCategoryId(), requestDTO.getName(), requestDTO.getShowName());
        List<AttributeTypeDO> attributeTypeDOS = attributeTypeMapper.selectList(queryWrapper);
        List<ListAttributeTypeResponseDTO> list = attributeTypeDOS.stream().map(doo -> doConvertDto(doo, ListAttributeTypeResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(list);
    }

}
