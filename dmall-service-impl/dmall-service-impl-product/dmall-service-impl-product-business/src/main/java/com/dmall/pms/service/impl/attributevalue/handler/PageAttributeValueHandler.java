package com.dmall.pms.service.impl.attributevalue.handler;

import com.dmall.pms.api.dto.attributevalue.common.CommonAttributeValueResponseDTO;
import com.dmall.pms.api.dto.attributevalue.request.PageAttributeValueRequestDTO;
import com.dmall.pms.generator.dataobject.AttributeValueDO;
import com.dmall.pms.generator.mapper.AttributeValueMapper;
import com.dmall.common.model.result.LayuiPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 属性值分页处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class PageAttributeValueHandler extends AbstractCommonHandler<PageAttributeValueRequestDTO, AttributeValueDO, CommonAttributeValueResponseDTO> {

    @Autowired
    private AttributeValueMapper attributeValueMapper;

    @Override
    public BaseResult<LayuiPage<CommonAttributeValueResponseDTO>> validate(PageAttributeValueRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<LayuiPage<CommonAttributeValueResponseDTO>> processor(PageAttributeValueRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
