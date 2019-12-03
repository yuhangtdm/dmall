package com.dmall.pms.service.impl.skumedia;

import com.dmall.pms.api.dto.skumedia.request.SaveSkuMediaRequestDTO;
import com.dmall.pms.api.dto.skumedia.request.UpdateSkuMediaRequestDTO;
import com.dmall.pms.api.dto.skumedia.request.ListSkuMediaRequestDTO;
import com.dmall.pms.api.dto.skumedia.request.PageSkuMediaRequestDTO;
import com.dmall.pms.api.dto.skumedia.common.CommonSkuMediaResponseDTO;
import com.dmall.pms.api.service.SkuMediaService;
import com.dmall.pms.service.impl.skumedia.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: sku媒体对象服务实现
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@RestController
public class  SkuMediaServiceImpl implements SkuMediaService {

    @Autowired
    protected SaveSkuMediaHandler saveSkuMediaHandler;

    @Autowired
    private DeleteSkuMediaHandler deleteSkuMediaHandler;

    @Autowired
    private UpdateSkuMediaHandler updateSkuMediaHandler;

    @Autowired
    private GetSkuMediaHandler getSkuMediaHandler;

    @Autowired
    private ListSkuMediaHandler listSkuMediaHandler;

    @Autowired
    private PageSkuMediaHandler pageSkuMediaHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveSkuMediaRequestDTO requestDTO) {
        return saveSkuMediaHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteSkuMediaHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateSkuMediaRequestDTO requestDTO) {
        return updateSkuMediaHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonSkuMediaResponseDTO> get(Long id) {
        return getSkuMediaHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonSkuMediaResponseDTO>> list(@RequestBody ListSkuMediaRequestDTO requestDTO) {
        return listSkuMediaHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<CommonSkuMediaResponseDTO>> page(@RequestBody PageSkuMediaRequestDTO requestDTO) {
        return pageSkuMediaHandler.handler(requestDTO);
    }

}
