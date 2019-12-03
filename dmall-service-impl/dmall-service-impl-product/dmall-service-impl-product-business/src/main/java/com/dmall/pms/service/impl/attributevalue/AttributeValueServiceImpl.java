package com.dmall.pms.service.impl.attributevalue;

import com.dmall.pms.api.dto.attributevalue.request.SaveAttributeValueRequestDTO;
import com.dmall.pms.api.dto.attributevalue.request.UpdateAttributeValueRequestDTO;
import com.dmall.pms.api.dto.attributevalue.request.ListAttributeValueRequestDTO;
import com.dmall.pms.api.dto.attributevalue.request.PageAttributeValueRequestDTO;
import com.dmall.pms.api.dto.attributevalue.common.CommonAttributeValueResponseDTO;
import com.dmall.pms.api.service.AttributeValueService;
import com.dmall.pms.service.impl.attributevalue.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 属性值服务实现
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@RestController
public class  AttributeValueServiceImpl implements AttributeValueService {

    @Autowired
    protected SaveAttributeValueHandler saveAttributeValueHandler;

    @Autowired
    private DeleteAttributeValueHandler deleteAttributeValueHandler;

    @Autowired
    private UpdateAttributeValueHandler updateAttributeValueHandler;

    @Autowired
    private GetAttributeValueHandler getAttributeValueHandler;

    @Autowired
    private ListAttributeValueHandler listAttributeValueHandler;

    @Autowired
    private PageAttributeValueHandler pageAttributeValueHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveAttributeValueRequestDTO requestDTO) {
        return saveAttributeValueHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteAttributeValueHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateAttributeValueRequestDTO requestDTO) {
        return updateAttributeValueHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonAttributeValueResponseDTO> get(Long id) {
        return getAttributeValueHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonAttributeValueResponseDTO>> list(@RequestBody ListAttributeValueRequestDTO requestDTO) {
        return listAttributeValueHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<CommonAttributeValueResponseDTO>> page(@RequestBody PageAttributeValueRequestDTO requestDTO) {
        return pageAttributeValueHandler.handler(requestDTO);
    }

}
