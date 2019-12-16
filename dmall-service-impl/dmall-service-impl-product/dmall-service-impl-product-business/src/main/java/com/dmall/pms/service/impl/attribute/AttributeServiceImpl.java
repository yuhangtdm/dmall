package com.dmall.pms.service.impl.attribute;

import com.dmall.pms.api.dto.attribute.request.SaveAttributeRequestDTO;
import com.dmall.pms.api.dto.attribute.request.UpdateAttributeRequestDTO;
import com.dmall.pms.api.dto.attribute.request.ListAttributeRequestDTO;
import com.dmall.pms.api.dto.attribute.common.CommonAttributeResponseDTO;
import com.dmall.pms.api.service.AttributeService;
import com.dmall.pms.service.impl.attribute.handler.*;
import com.dmall.common.model.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 属性服务实现
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@RestController
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    protected SaveAttributeHandler saveAttributeHandler;

    @Autowired
    private DeleteAttributeHandler deleteAttributeHandler;

    @Autowired
    private UpdateAttributeHandler updateAttributeHandler;

    @Autowired
    private GetAttributeHandler getAttributeHandler;

    @Autowired
    private ListAttributeHandler listAttributeHandler;

    @Override
    public BaseResult<Long> save(@RequestBody SaveAttributeRequestDTO requestDTO) {
        return saveAttributeHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteAttributeHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateAttributeRequestDTO requestDTO) {
        return updateAttributeHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonAttributeResponseDTO> get(Long id) {
        return getAttributeHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonAttributeResponseDTO>> list(@RequestBody ListAttributeRequestDTO requestDTO) {
        return listAttributeHandler.handler(requestDTO);
    }

}
