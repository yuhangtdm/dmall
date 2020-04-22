package com.dmall.pms.service.impl.product.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.util.BeanUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.pms.api.dto.product.response.get.BasicProductResponseDTO;
import com.dmall.pms.api.dto.product.response.get.GetProductResponseDTO;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.generator.mapper.ProductMapper;
import com.dmall.pms.service.impl.support.ProductAttributeValueSupport;
import com.dmall.pms.service.impl.support.SkuSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询商品处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class GetProductHandler extends AbstractCommonHandler<Long, ProductDO, GetProductResponseDTO> {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductAttributeValueSupport productAttributeValueSupport;

    @Autowired
    private SkuSupport skuSupport;

    @Override
    public BaseResult<GetProductResponseDTO> processor(Long id) {
        ProductDO productDO = productMapper.selectById(id);
        if (productDO == null) {
            return ResultUtil.fail(PmsErrorEnum.PRODUCT_NOT_EXISTS);
        }
        GetProductResponseDTO responseDTO = new GetProductResponseDTO();
        // 设置商品基本信息
        BasicProductResponseDTO basicProduct = BeanUtil.copyProperties(productDO, BasicProductResponseDTO.class);
        responseDTO.setBasicProduct(basicProduct);
        responseDTO.setExt(productAttributeValueSupport.getProductAttributeValue(id, productDO.getBrandId()));
        responseDTO.setSkuList(skuSupport.getSkuList(id));
        return ResultUtil.success(responseDTO);
    }

}
