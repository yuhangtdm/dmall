package com.dmall.pms.service.impl.skuattribute;

import com.dmall.pms.api.dto.skuattribute.request.SaveSkuAttributeRequestDTO;
import com.dmall.pms.api.dto.skuattribute.request.UpdateSkuAttributeRequestDTO;
import com.dmall.pms.api.dto.skuattribute.request.ListSkuAttributeRequestDTO;
import com.dmall.pms.api.dto.skuattribute.request.PageSkuAttributeRequestDTO;
import com.dmall.pms.api.dto.skuattribute.common.CommonSkuAttributeResponseDTO;
import com.dmall.pms.api.service.SkuAttributeService;
import com.dmall.pms.service.impl.skuattribute.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: sku属性服务实现
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@RestController
public class  SkuAttributeServiceImpl implements SkuAttributeService {

    @Autowired
    protected SaveSkuAttributeHandler saveSkuAttributeHandler;

    @Autowired
    private DeleteSkuAttributeHandler deleteSkuAttributeHandler;

    @Autowired
    private UpdateSkuAttributeHandler updateSkuAttributeHandler;

    @Autowired
    private GetSkuAttributeHandler getSkuAttributeHandler;

    @Autowired
    private ListSkuAttributeHandler listSkuAttributeHandler;

    @Autowired
    private PageSkuAttributeHandler pageSkuAttributeHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveSkuAttributeRequestDTO requestDTO) {
        return saveSkuAttributeHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteSkuAttributeHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateSkuAttributeRequestDTO requestDTO) {
        return updateSkuAttributeHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonSkuAttributeResponseDTO> get(Long id) {
        return getSkuAttributeHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonSkuAttributeResponseDTO>> list(@RequestBody ListSkuAttributeRequestDTO requestDTO) {
        return listSkuAttributeHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<CommonSkuAttributeResponseDTO>> page(@RequestBody PageSkuAttributeRequestDTO requestDTO) {
        return pageSkuAttributeHandler.handler(requestDTO);
    }

}
