package com.dmall.pms.service.impl.product.handler;

import com.dmall.pms.api.dto.product.common.CommonProductResponseDTO;
import com.dmall.pms.api.dto.product.request.ListProductRequestDTO;
import com.dmall.pms.service.impl.product.enums.ProductErrorEnum;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 商品列表处理器
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Component
public class ListProductHandler extends AbstractCommonHandler<ListProductRequestDTO, ProductDO, CommonProductResponseDTO> {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public BaseResult<List<CommonProductResponseDTO>> validate(ListProductRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonProductResponseDTO>> processor(ListProductRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
