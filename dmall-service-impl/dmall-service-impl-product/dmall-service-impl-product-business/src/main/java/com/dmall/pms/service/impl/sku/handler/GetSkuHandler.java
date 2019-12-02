package com.dmall.pms.service.impl.sku.handler;

import com.dmall.pms.api.dto.sku.common.CommonSkuResponseDTO;
import com.dmall.pms.service.impl.sku.enums.SkuErrorEnum;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.component.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 查询sku处理器
 * @author: created by hang.yu on 2019-12-02 23:18:01
 */
@Component
public class GetSkuHandler extends AbstractCommonHandler<Long, SkuDO, CommonSkuResponseDTO> {

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public BaseResult<CommonSkuResponseDTO> validate(Long id) {
        return ResultUtil.success();
    }

    @Override
    public BaseResult<CommonSkuResponseDTO> processor(Long id) {
        return ResultUtil.success();
    }

}
