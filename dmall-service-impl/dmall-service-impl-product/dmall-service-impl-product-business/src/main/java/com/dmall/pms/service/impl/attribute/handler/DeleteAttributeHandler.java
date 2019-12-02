package com.dmall.pms.service.impl.attribute.handler;

import com.dmall.pms.service.impl.attribute.enums.AttributeErrorEnum;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.mapper.AttributeMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 删除属性处理器
 * @author: created by hang.yu on 2019-12-02 23:17:59
 */
@Component
public class DeleteAttributeHandler extends AbstractCommonHandler<Long, AttributeDO, Long> {

    @Autowired
    private AttributeMapper attributeMapper;

    @Override
    public BaseResult<Long> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        return ResultUtil.success();
    }

}
