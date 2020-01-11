package com.dmall.bms.service.impl.resource.handler;

import com.dmall.bms.generator.dataobject.ResourceDO;
import com.dmall.bms.generator.mapper.ResourceMapper;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 删除资源处理器
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Component
public class DeleteResourceHandler extends AbstractCommonHandler<Long, ResourceDO, Long> {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public BaseResult<Long> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        return ResultUtil.success();
    }

}