package com.dmall.pms.service.impl.productattribute.handler;

import com.dmall.pms.api.dto.productattribute.common.CommonProductAttributeResponseDTO;
import com.dmall.pms.api.dto.productattribute.request.ListProductAttributeRequestDTO;
import com.dmall.pms.generator.dataobject.ProductAttributeDO;
import com.dmall.pms.generator.mapper.ProductAttributeMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 属性值列表处理器
 * @author: created by hang.yu on 2019-12-07 21:04:46
 */
@Component
public class ListProductAttributeHandler extends AbstractCommonHandler<ListProductAttributeRequestDTO, ProductAttributeDO, CommonProductAttributeResponseDTO> {

    @Autowired
    private ProductAttributeMapper productAttributeMapper;

    @Override
    public BaseResult<List<CommonProductAttributeResponseDTO>> validate(ListProductAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonProductAttributeResponseDTO>> processor(ListProductAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
