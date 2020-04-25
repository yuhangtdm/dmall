package com.dmall.pms.service.support;

import com.dmall.pms.api.enums.AuditTypeEnum;
import com.dmall.pms.api.enums.SkuAuditStatusEnum;
import com.dmall.pms.generator.dataobject.SkuAuditDO;
import com.dmall.pms.generator.mapper.SkuAuditMapper;
import com.dmall.pms.service.validate.PmsValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: SkuAuditSupport
 * @author: created by hang.yu on 2020/4/25 15:12
 */
@Component
public class SkuAuditSupport {

    @Autowired
    private SkuAuditMapper skuAuditMapper;

    @Autowired
    private PmsValidate pmsValidate;

    public void insert(Long productId, Long skuId, AuditTypeEnum auditTypeEnum) {
        pmsValidate.validateProduct(productId);
        pmsValidate.validateSku(skuId);
        SkuAuditDO skuAuditDO = new SkuAuditDO();
        skuAuditDO.setProductId(productId);
        skuAuditDO.setSkuId(skuId);
        skuAuditDO.setReason(auditTypeEnum.getDesc());
        skuAuditDO.setAuditType(auditTypeEnum.getCode());
        skuAuditDO.setStatus(SkuAuditStatusEnum.NOT_AUDIT.getCode());
        skuAuditMapper.insert(skuAuditDO);
    }
}
