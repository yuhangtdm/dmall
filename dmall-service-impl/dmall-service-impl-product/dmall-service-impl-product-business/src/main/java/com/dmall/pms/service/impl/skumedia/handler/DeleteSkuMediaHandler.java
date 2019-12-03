package com.dmall.pms.service.impl.skumedia.handler;

import com.dmall.pms.service.impl.skumedia.enums.SkuMediaErrorEnum;
import com.dmall.pms.generator.dataobject.SkuMediaDO;
import com.dmall.pms.generator.mapper.SkuMediaMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 删除sku媒体对象处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class DeleteSkuMediaHandler extends AbstractCommonHandler<Long, SkuMediaDO, Long> {

    @Autowired
    private SkuMediaMapper skuMediaMapper;

    @Override
    public BaseResult<Long> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(Long id) {
        return ResultUtil.success();
    }

}
