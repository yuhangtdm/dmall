package com.dmall.pms.service.impl.product.handler;

import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.handler.BeanUtil;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.product.response.GetProductAttributeResponseDTO;
import com.dmall.pms.api.dto.product.response.get.BasicProductResponseDTO;
import com.dmall.pms.api.dto.product.response.get.GetProductResponseDTO;
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
public class GetProductHandler extends AbstractCommonHandler<Long, ProductDO, GetProductResponseDTO> {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductAttributeSupport productAttributeSupport;

    @Override
    public BaseResult<GetProductResponseDTO> processor(Long id) {
        ProductDO productDO = productMapper.selectById(id);
        if (productDO == null) {
            return ResultUtil.fail(ProductErrorEnum.PRODUCT_NOT_EXIST);
        }
        GetProductResponseDTO responseDTO = new GetProductResponseDTO();
        // 设置商品基本信息
        BasicProductResponseDTO basicProduct = BeanUtil.copyProperties(productDO, BasicProductResponseDTO.class);
        responseDTO.setBasicProduct(basicProduct);
        // 设置商品销售规格和参数
        setSaleAttribute(responseDTO, id);
        //todo 设置sku
        return ResultUtil.success(responseDTO);
    }

    /**
     * 设置销售规格和参数
     */
    private void setSaleAttribute(GetProductResponseDTO responseDTO, Long id) {
//        GetProductAttributeResponseDTO attributeResponseDTO = productAttributeSupport.setSaleAttribute(id);
//        responseDTO.setSpecifications(attributeResponseDTO.getSpecifications());
//        responseDTO.setParams(attributeResponseDTO.getParams());
    }

}
