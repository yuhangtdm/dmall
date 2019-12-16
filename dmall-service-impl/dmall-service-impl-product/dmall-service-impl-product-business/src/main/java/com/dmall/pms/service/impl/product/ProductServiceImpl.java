package com.dmall.pms.service.impl.product;

import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.pms.api.dto.product.request.PageProductRequestDTO;
import com.dmall.pms.api.dto.product.request.save.SaveProductRequestDTO;
import com.dmall.pms.api.dto.product.request.update.UpdateProductRequestDTO;
import com.dmall.pms.api.dto.product.response.PageProductResponseDTO;
import com.dmall.pms.api.dto.product.response.get.GetProductResponseDTO;
import com.dmall.pms.api.service.ProductService;
import com.dmall.pms.service.impl.product.handler.GetProductHandler;
import com.dmall.pms.service.impl.product.handler.PageProductHandler;
import com.dmall.pms.service.impl.product.handler.SaveProductHandler;
import com.dmall.pms.service.impl.product.handler.UpdateProductHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 商品服务实现
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@RestController
public class ProductServiceImpl implements ProductService {

    @Autowired
    protected SaveProductHandler saveProductHandler;

    @Autowired
    private UpdateProductHandler updateProductHandler;

    @Autowired
    private GetProductHandler getProductHandler;

    @Autowired
    private PageProductHandler pageProductHandler;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> save(@RequestBody SaveProductRequestDTO requestDTO) {
        return saveProductHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<PageProductResponseDTO>> page(@RequestBody PageProductRequestDTO requestDTO) {
        return pageProductHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> update(@RequestBody UpdateProductRequestDTO requestDTO) {
        return updateProductHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<GetProductResponseDTO> get(Long id) {
        return getProductHandler.handler(id);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return null;
    }


}
