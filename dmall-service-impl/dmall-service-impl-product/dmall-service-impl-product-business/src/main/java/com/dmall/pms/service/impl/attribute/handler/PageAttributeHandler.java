package com.dmall.pms.service.impl.attribute.handler;

import com.dmall.pms.api.dto.attribute.common.CommonAttributeResponseDTO;
import com.dmall.pms.api.dto.attribute.request.PageAttributeRequestDTO;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.mapper.AttributeMapper;
import com.dmall.common.model.result.LayuiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 属性分页处理器
 * @author: created by hang.yu on 2019-12-16 15:14:49
 */
@Component
public class PageAttributeHandler extends AbstractCommonHandler<PageAttributeRequestDTO, AttributeDO, CommonAttributeResponseDTO> {

    @Autowired
    private AttributeMapper attributeMapper;

    @Override
    public BaseResult<LayuiPage<CommonAttributeResponseDTO>> validate(PageAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<CommonAttributeResponseDTO>> processor(PageAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
