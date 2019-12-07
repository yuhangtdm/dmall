package com.dmall.pms.service.impl.productattribute;

import com.dmall.pms.api.dto.productattribute.request.SaveProductAttributeRequestDTO;
import com.dmall.pms.api.dto.productattribute.request.UpdateProductAttributeRequestDTO;
import com.dmall.pms.api.dto.productattribute.request.ListProductAttributeRequestDTO;
import com.dmall.pms.api.dto.productattribute.request.PageProductAttributeRequestDTO;
import com.dmall.pms.api.dto.productattribute.common.CommonProductAttributeResponseDTO;
import com.dmall.pms.api.service.ProductAttributeService;
import com.dmall.pms.service.impl.productattribute.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @description: 属性值服务实现
 * @author: created by hang.yu on 2019-12-07 21:04:46
 */
@RestController
public class  ProductAttributeServiceImpl implements ProductAttributeService {

    @Autowired
    protected SaveProductAttributeHandler saveProductAttributeHandler;

    @Autowired
    private DeleteProductAttributeHandler deleteProductAttributeHandler;

    @Autowired
    private UpdateProductAttributeHandler updateProductAttributeHandler;

    @Autowired
    private GetProductAttributeHandler getProductAttributeHandler;

    @Autowired
    private ListProductAttributeHandler listProductAttributeHandler;

    @Autowired
    private PageProductAttributeHandler pageProductAttributeHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveProductAttributeRequestDTO requestDTO) {
        return saveProductAttributeHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteProductAttributeHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateProductAttributeRequestDTO requestDTO) {
        return updateProductAttributeHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonProductAttributeResponseDTO> get(Long id) {
        return getProductAttributeHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonProductAttributeResponseDTO>> list(@RequestBody ListProductAttributeRequestDTO requestDTO) {
        return listProductAttributeHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<CommonProductAttributeResponseDTO>> page(@RequestBody PageProductAttributeRequestDTO requestDTO) {
        return pageProductAttributeHandler.handler(requestDTO);
    }

}
