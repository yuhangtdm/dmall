package com.dmall.pms.service.impl.product.handler;

import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.service.impl.product.attribute.ProductAttributeSupport;
import com.dmall.pms.service.impl.product.enums.ProductErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询商品处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class GetProductAttributeHandler extends AbstractCommonHandler<Long, ProductDO, Void> {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductAttributeSupport productAttributeSupport;

    @Override
    public BaseResult<Void> processor(Long id) {
        ProductDO productDO = productMapper.selectById(id);
        if (productDO == null) {
            return ResultUtil.fail(ProductErrorEnum.PRODUCT_NOT_EXIST);
        }
//        GetProductAttributeResponseDTO responseDTO = productAttributeSupport.setSaleAttribute(id);
        return ResultUtil.success();
    }

}
