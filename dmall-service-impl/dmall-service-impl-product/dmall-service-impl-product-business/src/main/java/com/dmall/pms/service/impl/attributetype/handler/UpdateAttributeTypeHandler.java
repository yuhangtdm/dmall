package com.dmall.pms.service.impl.attributetype.handler;

import com.dmall.pms.api.dto.attributetype.request.UpdateAttributeTypeRequestDTO;
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
 * @description: 修改属性分类处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class UpdateAttributeTypeHandler extends AbstractCommonHandler<UpdateAttributeTypeRequestDTO, AttributeTypeDO, Long> {

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Override
    public BaseResult<Long> validate(UpdateAttributeTypeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(UpdateAttributeTypeRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
