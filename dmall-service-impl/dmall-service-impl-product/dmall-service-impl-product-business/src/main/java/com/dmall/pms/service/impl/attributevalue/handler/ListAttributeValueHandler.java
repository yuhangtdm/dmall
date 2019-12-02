package com.dmall.pms.service.impl.attributevalue.handler;

import com.dmall.pms.api.dto.attributevalue.common.CommonAttributeValueResponseDTO;
import com.dmall.pms.api.dto.attributevalue.request.ListAttributeValueRequestDTO;
import com.dmall.pms.service.impl.attributevalue.enums.AttributeValueErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeValueDO;
import com.dmall.pms.generator.mapper.AttributeValueMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @description: 属性值列表处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class ListAttributeValueHandler extends AbstractCommonHandler<ListAttributeValueRequestDTO, AttributeValueDO, CommonAttributeValueResponseDTO> {

    @Autowired
    private AttributeValueMapper attributeValueMapper;

    @Override
    public BaseResult<List<CommonAttributeValueResponseDTO>> validate(ListAttributeValueRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<List<CommonAttributeValueResponseDTO>> processor(ListAttributeValueRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
