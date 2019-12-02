package com.dmall.pms.service.impl.attribute.handler;

import com.dmall.pms.api.dto.attribute.common.CommonAttributeResponseDTO;
import com.dmall.pms.api.dto.attribute.request.ListAttributeRequestDTO;
import com.dmall.pms.service.impl.attribute.enums.AttributeErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.mapper.AttributeMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 属性列表处理器
 * @author: created by hang.yu on 2019-12-02 23:17:59
 */
@Component
public class ListAttributeHandler extends AbstractCommonHandler<ListAttributeRequestDTO, AttributeDO, CommonAttributeResponseDTO> {

    @Autowired
    private AttributeMapper attributeMapper;

    @Override
    public BaseResult<List<CommonAttributeResponseDTO>> validate(ListAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonAttributeResponseDTO>> processor(ListAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
