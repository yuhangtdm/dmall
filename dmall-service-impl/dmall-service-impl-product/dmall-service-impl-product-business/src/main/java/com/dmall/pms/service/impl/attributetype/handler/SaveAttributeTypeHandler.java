package com.dmall.pms.service.impl.attributetype.handler;

import com.dmall.pms.api.dto.attributetype.request.SaveAttributeTypeRequestDTO;
import com.dmall.pms.service.impl.attributetype.enums.AttributeTypeErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增属性分类处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class SaveAttributeTypeHandler extends AbstractCommonHandler<SaveAttributeTypeRequestDTO, AttributeTypeDO, Long> {

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Override
    public BaseResult<Long> validate(SaveAttributeTypeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveAttributeTypeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
