package com.dmall.pms.service.impl.product.handler;

import com.dmall.pms.api.dto.product.common.CommonProductResponseDTO;
import com.dmall.pms.service.impl.product.enums.ProductErrorEnum;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询商品处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class GetProductHandler extends AbstractCommonHandler<Long, ProductDO, CommonProductResponseDTO> {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public BaseResult<CommonProductResponseDTO> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<CommonProductResponseDTO> processor(Long id) {
        return ResultUtil.success();
    }

}
