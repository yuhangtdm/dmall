package com.dmall.bms.service.impl.merchants;

import com.dmall.bms.api.dto.merchants.request.SaveMerchantsRequestDTO;
import com.dmall.bms.api.dto.merchants.request.UpdateMerchantsRequestDTO;
import com.dmall.bms.api.dto.merchants.request.ListMerchantsRequestDTO;
import com.dmall.bms.api.dto.merchants.request.PageMerchantsRequestDTO;
import com.dmall.bms.api.dto.merchants.common.CommonMerchantsResponseDTO;
import com.dmall.bms.api.service.MerchantsService;
import com.dmall.bms.service.impl.merchants.handler.*;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 商家店铺表 1期只有一家店铺服务实现
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@RestController
public class MerchantsServiceImpl implements MerchantsService {

    @Autowired
    private SaveMerchantsHandler saveMerchantsHandler;

    @Autowired
    private DeleteMerchantsHandler deleteMerchantsHandler;

    @Autowired
    private UpdateMerchantsHandler updateMerchantsHandler;

    @Autowired
    private GetMerchantsHandler getMerchantsHandler;

    @Autowired
    private ListMerchantsHandler listMerchantsHandler;

    @Autowired
    private PageMerchantsHandler pageMerchantsHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveMerchantsRequestDTO requestDTO) {
        return saveMerchantsHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteMerchantsHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateMerchantsRequestDTO requestDTO) {
        return updateMerchantsHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonMerchantsResponseDTO> get(Long id) {
        return getMerchantsHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonMerchantsResponseDTO>> list(@RequestBody ListMerchantsRequestDTO requestDTO) {
        return listMerchantsHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayUiPage<CommonMerchantsResponseDTO>> page(@RequestBody PageMerchantsRequestDTO requestDTO) {
        return pageMerchantsHandler.handler(requestDTO);
    }

}
