package com.dmall.pms.service.impl.sku;

import com.dmall.pms.api.dto.sku.request.SaveSkuRequestDTO;
import com.dmall.pms.api.dto.sku.request.UpdateSkuRequestDTO;
import com.dmall.pms.api.dto.sku.request.ListSkuRequestDTO;
import com.dmall.pms.api.dto.sku.request.PageSkuRequestDTO;
import com.dmall.pms.api.dto.sku.common.CommonSkuResponseDTO;
import com.dmall.pms.api.service.SkuService;
import com.dmall.pms.service.impl.sku.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: sku服务实现
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@RestController
public class  SkuServiceImpl implements SkuService {

    @Autowired
    protected SaveSkuHandler saveSkuHandler;

    @Autowired
    private DeleteSkuHandler deleteSkuHandler;

    @Autowired
    private UpdateSkuHandler updateSkuHandler;

    @Autowired
    private GetSkuHandler getSkuHandler;

    @Autowired
    private ListSkuHandler listSkuHandler;

    @Autowired
    private PageSkuHandler pageSkuHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveSkuRequestDTO requestDTO) {
        return saveSkuHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteSkuHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateSkuRequestDTO requestDTO) {
        return updateSkuHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonSkuResponseDTO> get(Long id) {
        return getSkuHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonSkuResponseDTO>> list(@RequestBody ListSkuRequestDTO requestDTO) {
        return listSkuHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<CommonSkuResponseDTO>> page(@RequestBody PageSkuRequestDTO requestDTO) {
        return pageSkuHandler.handler(requestDTO);
    }

}
