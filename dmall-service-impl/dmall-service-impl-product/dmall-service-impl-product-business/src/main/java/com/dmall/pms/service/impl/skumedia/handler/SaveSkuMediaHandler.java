package com.dmall.pms.service.impl.skumedia.handler;

import com.dmall.pms.api.dto.skumedia.request.SaveSkuMediaRequestDTO;
import com.dmall.pms.service.impl.skumedia.enums.SkuMediaErrorEnum;
import com.dmall.pms.generator.dataobject.SkuMediaDO;
import com.dmall.pms.generator.mapper.SkuMediaMapper;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增sku媒体对象处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class SaveSkuMediaHandler extends AbstractCommonHandler<SaveSkuMediaRequestDTO, SkuMediaDO, Long> {

    @Autowired
    private SkuMediaMapper skuMediaMapper;

    @Override
    public BaseResult<Long> validate(SaveSkuMediaRequestDTO requestDTO) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<Long> processor(SaveSkuMediaRequestDTO requestDTO) {
        return ResultUtil.success();
    }

}
