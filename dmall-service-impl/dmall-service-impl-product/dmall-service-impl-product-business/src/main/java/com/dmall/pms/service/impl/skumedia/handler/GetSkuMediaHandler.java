package com.dmall.pms.service.impl.skumedia.handler;

import com.dmall.pms.api.dto.skumedia.common.CommonSkuMediaResponseDTO;
import com.dmall.pms.service.impl.skumedia.enums.SkuMediaErrorEnum;
import com.dmall.pms.generator.dataobject.SkuMediaDO;
import com.dmall.pms.generator.mapper.SkuMediaMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询sku媒体对象处理器
 * @author: created by hang.yu on 2019-12-03 19:56:06
 */
@Component
public class GetSkuMediaHandler extends AbstractCommonHandler<Long, SkuMediaDO, CommonSkuMediaResponseDTO> {

    @Autowired
    private SkuMediaMapper skuMediaMapper;

    @Override
    public BaseResult<CommonSkuMediaResponseDTO> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<CommonSkuMediaResponseDTO> processor(Long id) {
        return ResultUtil.success();
    }

}
