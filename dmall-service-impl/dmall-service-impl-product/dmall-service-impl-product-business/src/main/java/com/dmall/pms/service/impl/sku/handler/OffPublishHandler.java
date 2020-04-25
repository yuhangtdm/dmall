package com.dmall.pms.service.impl.sku.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.enums.AuditTypeEnum;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.generator.mapper.SkuMapper;
import com.dmall.pms.service.support.SkuAuditSupport;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: 下架处理器
 * @author: created by hang.yu on 2020/4/25 15:29
 */
@Component
public class OffPublishHandler extends AbstractCommonHandler<Long, SkuDO, Long> {

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SkuAuditSupport skuAuditSupport;

    @Autowired
    private PmsValidate pmsValidate;

    @Override
    public BaseResult processor(Long skuId) {
        SkuDO skuDO = pmsValidate.validateSku(skuId);
        skuDO.setPublishStatus(YNEnum.N.getCode());
        skuDO.setOffPublishTime(new Date());
        skuMapper.updateById(skuDO);
        skuAuditSupport.insert(skuDO.getProductId(), skuDO.getId(), AuditTypeEnum.SKU_OFF_PUBLISH);
        return ResultUtil.success(skuId);
    }
}
