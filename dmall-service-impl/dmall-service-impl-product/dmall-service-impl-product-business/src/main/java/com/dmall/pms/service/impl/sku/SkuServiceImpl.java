package com.dmall.pms.service.impl.sku;

import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.pms.api.dto.sku.common.CommonSkuResponseDTO;
import com.dmall.pms.api.dto.sku.request.PageSkuRequestDTO;
import com.dmall.pms.api.dto.sku.request.save.SaveSkuAttributeRequestDTO;
import com.dmall.pms.api.dto.sku.request.save.SaveSkuExtRequestDTO;
import com.dmall.pms.api.dto.sku.request.save.SaveSkuRequestDTO;
import com.dmall.pms.api.dto.sku.request.update.UpdateSkuRequestDTO;
import com.dmall.pms.api.dto.sku.response.PageSkuResponseDTO;
import com.dmall.pms.api.dto.sku.response.get.GetSkuResponseDTO;
import com.dmall.pms.api.service.SkuService;
import com.dmall.pms.service.impl.sku.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @description: sku服务实现
 * @author: created by hang.yu on 2019-12-16 15:14:50
 */
@RestController
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SaveOrUpdateSkuHandler saveOrUpdateSkuHandler;

    @Autowired
    private UpdateSkuHandler updateSkuHandler;

    @Autowired
    private GetSkuHandler getSkuHandler;

    @Autowired
    private PageSkuHandler pageSkuHandler;

    @Autowired
    private SaveSkuAttributeHandler saveSkuAttributeHandler;

    @Autowired
    private SaveSkuExtHandler saveSkuExtHandler;

    @Override
    public BaseResult<Long> saveOrUpdate(@RequestBody SaveSkuRequestDTO requestDTO) {
        return saveOrUpdateSkuHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> saveSkuAttribute(@Valid SaveSkuAttributeRequestDTO requestDTO) {
        return saveSkuAttributeHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> saveSkuExt(@Valid SaveSkuExtRequestDTO requestDTO) {
        return saveSkuExtHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateSkuRequestDTO requestDTO) {
        return updateSkuHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<PageSkuResponseDTO>> page(@RequestBody PageSkuRequestDTO requestDTO) {
        return pageSkuHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<GetSkuResponseDTO> get(Long id) {
        return getSkuHandler.handler(id);
    }

}
