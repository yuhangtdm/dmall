package com.dmall.bms.service.impl.resource;

import com.dmall.bms.api.dto.resource.request.SaveResourceRequestDTO;
import com.dmall.bms.api.dto.resource.request.UpdateResourceRequestDTO;
import com.dmall.bms.api.dto.resource.request.ListResourceRequestDTO;
import com.dmall.bms.api.dto.resource.request.PageResourceRequestDTO;
import com.dmall.bms.api.dto.resource.common.CommonResourceResponseDTO;
import com.dmall.bms.api.service.ResourceService;
import com.dmall.bms.service.impl.resource.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 资源服务实现
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@RestController
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private SaveResourceHandler saveResourceHandler;

    @Autowired
    private DeleteResourceHandler deleteResourceHandler;

    @Autowired
    private UpdateResourceHandler updateResourceHandler;

    @Autowired
    private GetResourceHandler getResourceHandler;

    @Autowired
    private ListResourceHandler listResourceHandler;

    @Autowired
    private PageResourceHandler pageResourceHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveResourceRequestDTO requestDTO) {
        return saveResourceHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteResourceHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateResourceRequestDTO requestDTO) {
        return updateResourceHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonResourceResponseDTO> get(Long id) {
        return getResourceHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonResourceResponseDTO>> list(@RequestBody ListResourceRequestDTO requestDTO) {
        return listResourceHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<CommonResourceResponseDTO>> page(@RequestBody PageResourceRequestDTO requestDTO) {
        return pageResourceHandler.handler(requestDTO);
    }

}
