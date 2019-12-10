package com.dmall.pms.service.impl.product;

import com.dmall.pms.api.dto.product.request.save.SaveProductRequestDTO;
import com.dmall.pms.api.dto.product.request.UpdateProductRequestDTO;
import com.dmall.pms.api.dto.product.request.ListProductRequestDTO;
import com.dmall.pms.api.dto.product.request.PageProductRequestDTO;
import com.dmall.pms.api.dto.product.common.CommonProductResponseDTO;
import com.dmall.pms.api.service.ProductService;
import com.dmall.pms.service.impl.product.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 商品服务实现
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@RestController
public class  ProductServiceImpl implements ProductService {

    @Autowired
    protected SaveProductHandler saveProductHandler;

    @Autowired
    private DeleteProductHandler deleteProductHandler;

    @Autowired
    private UpdateProductHandler updateProductHandler;

    @Autowired
    private GetProductHandler getProductHandler;

    @Autowired
    private ListProductHandler listProductHandler;

    @Autowired
    private PageProductHandler pageProductHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveProductRequestDTO requestDTO) {
        return saveProductHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteProductHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateProductRequestDTO requestDTO) {
        return updateProductHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonProductResponseDTO> get(Long id) {
        return getProductHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonProductResponseDTO>> list(@RequestBody ListProductRequestDTO requestDTO) {
        return listProductHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<CommonProductResponseDTO>> page(@RequestBody PageProductRequestDTO requestDTO) {
        return pageProductHandler.handler(requestDTO);
    }

}
