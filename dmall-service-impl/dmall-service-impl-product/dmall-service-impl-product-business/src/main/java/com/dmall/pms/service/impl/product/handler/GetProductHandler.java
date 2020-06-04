package com.dmall.pms.service.impl.product.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.BeanUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.product.response.get.BasicProductResponseDTO;
import com.dmall.pms.api.dto.product.response.get.GetProductResponseDTO;
import com.dmall.pms.generator.dataobject.ProductDO;
import com.dmall.pms.service.support.ProductAttributeValueSupport;
import com.dmall.pms.service.support.SkuSupport;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询商品处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class GetProductHandler extends AbstractCommonHandler<Long, ProductDO, GetProductResponseDTO> {

    @Autowired
    private ProductAttributeValueSupport productAttributeValueSupport;

    @Autowired
    private SkuSupport skuSupport;

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult<GetProductResponseDTO> processor(Long id) {
        ProductDO productDO = pmsValidate.validateProduct(id);
        GetProductResponseDTO responseDTO = new GetProductResponseDTO();
        // 设置商品基本信息
        BasicProductResponseDTO basicProduct = BeanUtil.copyProperties(productDO, BasicProductResponseDTO.class);
        responseDTO.setBasicProduct(basicProduct);
        // 扩展信息
        responseDTO.setExt(productAttributeValueSupport.getProductAttributeValue(id, productDO.getBrandId()));
        // sku列表
        responseDTO.setSkuList(skuSupport.getSkuList(id));
        return ResultUtil.success(responseDTO);
    }

}
