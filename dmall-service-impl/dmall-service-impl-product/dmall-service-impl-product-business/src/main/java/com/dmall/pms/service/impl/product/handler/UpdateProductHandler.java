package com.dmall.pms.service.impl.product.handler;

import com.dmall.pms.api.dto.product.request.UpdateProductRequestDTO;
import com.dmall.pms.service.impl.product.enums.ProductErrorEnum;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 修改商品处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class UpdateProductHandler extends AbstractCommonHandler<UpdateProductRequestDTO, ProductDO, Long> {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public BaseResult<Long> validate(UpdateProductRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateProductRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
