package com.dmall.pms.service.impl.attributevalue.handler;

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
 * @description: 删除属性值处理器
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Component
public class DeleteAttributeValueHandler extends AbstractCommonHandler<Long, AttributeValueDO, Long> {

    @Autowired
    private AttributeValueMapper attributeValueMapper;

    @Override
    public BaseResult<Long> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        return ResultUtil.success();
    }

}
