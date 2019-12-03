package com.dmall.pms.service.impl.attributevalue.handler;

import com.dmall.pms.api.dto.attributevalue.common.CommonAttributeValueResponseDTO;
import com.dmall.pms.service.impl.attributevalue.enums.AttributeValueErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeValueDO;
import com.dmall.pms.generator.mapper.AttributeValueMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询属性值处理器
 * @author: created by hang.yu on 2019-12-03 19:56:05
 */
@Component
public class GetAttributeValueHandler extends AbstractCommonHandler<Long, AttributeValueDO, CommonAttributeValueResponseDTO> {

    @Autowired
    private AttributeValueMapper attributeValueMapper;

    @Override
    public BaseResult<CommonAttributeValueResponseDTO> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<CommonAttributeValueResponseDTO> processor(Long id) {
        return ResultUtil.success();
    }

}
