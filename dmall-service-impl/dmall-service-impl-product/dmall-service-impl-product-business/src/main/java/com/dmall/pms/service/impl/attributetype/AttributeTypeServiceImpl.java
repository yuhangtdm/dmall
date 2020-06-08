package com.dmall.pms.service.impl.attributetype;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.pms.api.dto.attributetype.request.*;
import com.dmall.pms.api.dto.attributetype.response.AttributeTypeResponseDTO;
import com.dmall.pms.api.service.AttributeTypeService;
import com.dmall.pms.service.impl.attributetype.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 属性类别服务实现
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@RestController
public class AttributeTypeServiceImpl implements AttributeTypeService {

    @Autowired
    private SaveAttributeTypeHandler saveAttributeTypeHandler;

    @Autowired
    private DeleteAttributeTypeHandler deleteAttributeTypeHandler;

    @Autowired
    private UpdateAttributeTypeHandler updateAttributeTypeHandler;

    @Autowired
    private GetAttributeTypeHandler getAttributeTypeHandler;

    @Autowired
    private ListAttributeTypeHandler listAttributeTypeHandler;

    @Autowired
    private PageAttributeTypeHandler pageAttributeTypeHandler;

    @Autowired
    private BatchSaveAttributeTypeHandler batchSaveAttributeTypeHandler;

    @Override
    public BaseResult<Long> save(@RequestBody SaveAttributeTypeRequestDTO requestDTO) {
        return saveAttributeTypeHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> batchSave(@Valid BatchSaveAttributeTypeRequestDTO requestDTO) {
        return batchSaveAttributeTypeHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(Long id) {
        return deleteAttributeTypeHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@RequestBody UpdateAttributeTypeRequestDTO requestDTO) {
        return updateAttributeTypeHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<AttributeTypeResponseDTO> get(Long id) {
        return getAttributeTypeHandler.handler(id);
    }

    @Override
    public BaseResult<List<AttributeTypeResponseDTO>> list(@RequestBody ListAttributeTypeRequestDTO requestDTO) {
        return listAttributeTypeHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<ResponsePage<AttributeTypeResponseDTO>>
        page(@RequestBody PageAttributeTypeRequestDTO requestDTO) {
        return pageAttributeTypeHandler.handler(requestDTO);
    }

}
