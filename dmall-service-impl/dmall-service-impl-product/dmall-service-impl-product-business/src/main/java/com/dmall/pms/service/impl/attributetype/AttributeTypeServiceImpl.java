package com.dmall.pms.service.impl.attributetype;

import com.dmall.pms.api.dto.attributetype.request.SaveAttributeTypeRequestDTO;
import com.dmall.pms.api.dto.attributetype.request.UpdateAttributeTypeRequestDTO;
import com.dmall.pms.api.dto.attributetype.request.ListAttributeTypeRequestDTO;
import com.dmall.pms.api.dto.attributetype.request.PageAttributeTypeRequestDTO;
import com.dmall.pms.api.dto.attributetype.common.CommonAttributeTypeResponseDTO;
import com.dmall.pms.api.service.AttributeTypeService;
import com.dmall.pms.service.impl.attributetype.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 属性分类服务实现
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@RestController
public class  AttributeTypeServiceImpl implements AttributeTypeService {

    @Autowired
    protected SaveAttributeTypeHandler saveAttributeTypeHandler;

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
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteAttributeTypeHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateAttributeTypeRequestDTO requestDTO) {
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
    public BaseResult<LayuiPage<CommonAttributeTypeResponseDTO>> page(@RequestBody PageAttributeTypeRequestDTO requestDTO) {
        return pageAttributeTypeHandler.handler(requestDTO);
    }

}
