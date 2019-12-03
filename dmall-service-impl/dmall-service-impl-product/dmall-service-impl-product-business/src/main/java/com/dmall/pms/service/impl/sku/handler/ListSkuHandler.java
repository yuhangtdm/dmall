package com.dmall.pms.service.impl.sku.handler;

import com.dmall.pms.api.dto.sku.common.CommonSkuResponseDTO;
import com.dmall.pms.api.dto.sku.request.ListSkuRequestDTO;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: sku列表处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class ListSkuHandler extends AbstractCommonHandler<ListSkuRequestDTO, SkuDO, CommonSkuResponseDTO> {

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public BaseResult<List<CommonSkuResponseDTO>> validate(ListSkuRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonSkuResponseDTO>> processor(ListSkuRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
