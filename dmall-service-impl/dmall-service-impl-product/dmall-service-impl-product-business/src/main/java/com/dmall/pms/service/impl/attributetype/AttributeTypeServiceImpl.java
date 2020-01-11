package com.dmall.pms.service.impl.attributetype;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.LayUiPage;
import com.dmall.pms.api.dto.attributetype.common.CommonAttributeTypeResponseDTO;
import com.dmall.pms.api.dto.attributetype.request.ListAttributeTypeRequestDTO;
import com.dmall.pms.api.dto.attributetype.request.PageAttributeTypeRequestDTO;
import com.dmall.pms.api.dto.attributetype.request.SaveAttributeTypeRequestDTO;
import com.dmall.pms.api.dto.attributetype.request.UpdateAttributeTypeRequestDTO;
import com.dmall.pms.api.dto.attributetype.response.PageAttributeTypeResponseDTO;
import com.dmall.pms.api.service.AttributeTypeService;
import com.dmall.pms.service.impl.attributetype.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 属性分类服务实现
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

    @Override
    public BaseResult<Long> save(@RequestBody SaveAttributeTypeRequestDTO requestDTO) {
        return saveAttributeTypeHandler.handler(requestDTO);
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
    public BaseResult<CommonAttributeTypeResponseDTO> get(Long id) {
        return getAttributeTypeHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonAttributeTypeResponseDTO>> list(@RequestBody ListAttributeTypeRequestDTO requestDTO) {
        return listAttributeTypeHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayUiPage<PageAttributeTypeResponseDTO>> page(@RequestBody PageAttributeTypeRequestDTO requestDTO) {
        return pageAttributeTypeHandler.handler(requestDTO);
    }

}
