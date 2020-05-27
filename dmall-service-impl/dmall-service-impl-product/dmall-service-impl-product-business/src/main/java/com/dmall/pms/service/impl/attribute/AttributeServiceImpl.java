package com.dmall.pms.service.impl.attribute;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.pms.api.dto.attribute.request.*;
import com.dmall.pms.api.dto.attribute.response.AttributeResponseDTO;
import com.dmall.pms.api.service.AttributeService;
import com.dmall.pms.service.impl.attribute.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    private SaveAttributeHandler saveAttributeHandler;

    @Autowired
    private DeleteAttributeHandler deleteAttributeHandler;

    @Autowired
    private UpdateAttributeHandler updateAttributeHandler;

    @Autowired
    private GetAttributeHandler getAttributeHandler;

    @Autowired
    private ListAttributeHandler listAttributeHandler;

    @Autowired
    private PageAttributeHandler pageAttributeHandler;

    @Autowired
    private SetAttributeCategoryHandler setAttributeCategoryHandler;

    @Autowired
    private GetCategoryIdsHandler getCategoryIdsHandler;

    @Override
    public BaseResult<Long> save(@RequestBody SaveAttributeRequestDTO requestDTO) {
        return saveAttributeHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> delete(Long id) {
        return deleteAttributeHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateAttributeRequestDTO requestDTO) {
        return updateAttributeHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<AttributeResponseDTO> get(Long id) {
        return getAttributeHandler.handler(id);
    }

    @Override
    public BaseResult<List<AttributeResponseDTO>> list(@RequestBody ListAttributeRequestDTO requestDTO) {
        return listAttributeHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<ResponsePage<AttributeResponseDTO>> page(PageAttributeRequestDTO requestDTO) {
        return pageAttributeHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Void> setCategory(@RequestBody SetAttributeCategoryRequestDTO requestDTO) {
        return setAttributeCategoryHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<List<String>> getCategoryIds(Long id) {
        return getCategoryIdsHandler.handler(id);
    }

}
