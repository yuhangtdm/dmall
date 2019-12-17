package com.dmall.pms.service.impl.product;

import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.common.model.result.UploadResult;
import com.dmall.pms.api.dto.product.request.PageProductRequestDTO;
import com.dmall.pms.api.dto.product.request.save.SaveProductRequestDTO;
import com.dmall.pms.api.dto.product.request.update.UpdateProductRequestDTO;
import com.dmall.pms.api.dto.product.response.GetProductAttributeResponseDTO;
import com.dmall.pms.api.dto.product.response.PageProductResponseDTO;
import com.dmall.pms.api.dto.product.response.get.GetProductResponseDTO;
import com.dmall.pms.api.service.ProductService;
import com.dmall.pms.service.impl.product.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 商品服务实现
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@RestController
public class ProductServiceImpl implements ProductService {

    @Autowired
    private SaveProductHandler saveProductHandler;

    @Autowired
    private UpdateProductHandler updateProductHandler;

    @Autowired
    private GetProductHandler getProductHandler;

    @Autowired
    private PageProductHandler pageProductHandler;

    @Autowired
    private DeleteProductHandler deleteProductHandler;

    @Autowired
    private UploadProductPicHandler uploadProductPicHandler;


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
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> delete(Long id) {
        return deleteProductHandler.handler(id);
    }

    @Override
    public BaseResult<GetProductAttributeResponseDTO> getProductAttribute(Long id) {
        return null;
    }

    @Override
    public BaseResult<UploadResult> uploadProductPic(MultipartFile file) {
        return uploadProductPicHandler.handler(file);
    }

    @Override
    public BaseResult<UploadResult> uploadProductAttributePic(MultipartFile file) {
        return null;
    }

}
