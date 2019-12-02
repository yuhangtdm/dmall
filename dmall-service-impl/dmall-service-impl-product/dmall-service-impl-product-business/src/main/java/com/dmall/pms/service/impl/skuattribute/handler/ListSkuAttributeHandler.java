package com.dmall.pms.service.impl.skuattribute.handler;

import com.dmall.pms.api.dto.skuattribute.common.CommonSkuAttributeResponseDTO;
import com.dmall.pms.api.dto.skuattribute.request.ListSkuAttributeRequestDTO;
import com.dmall.pms.service.impl.skuattribute.enums.SkuAttributeErrorEnum;
import com.dmall.pms.generator.dataobject.SkuAttributeDO;
import com.dmall.pms.generator.mapper.SkuAttributeMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: sku属性列表处理器
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Component
public class ListSkuAttributeHandler extends AbstractCommonHandler<ListSkuAttributeRequestDTO, SkuAttributeDO, CommonSkuAttributeResponseDTO> {

    @Autowired
    private SkuAttributeMapper skuAttributeMapper;

    @Override
    public BaseResult<List<CommonSkuAttributeResponseDTO>> validate(ListSkuAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonSkuAttributeResponseDTO>> processor(ListSkuAttributeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
