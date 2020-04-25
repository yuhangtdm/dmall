package com.dmall.pms.service.impl.skuaudit.handler;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.enums.YNEnum;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.skuaudit.request.AuditRequestDTO;
import com.dmall.pms.api.enums.SkuAuditStatusEnum;
import com.dmall.pms.generator.dataobject.SkuAuditDO;
import com.dmall.pms.generator.mapper.SkuAuditMapper;
import com.dmall.pms.service.support.SkuImportEsSupport;
import com.dmall.pms.service.validate.PmsValidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 新增sku审核记录处理器
 * @author: created by hang.yu on 2020-04-25 14:49:35
 */
@Slf4j
@Component
public class AuditHandler extends AbstractCommonHandler<AuditRequestDTO, SkuAuditDO, Long> {

    @Autowired
    private SkuAuditMapper skuAuditMapper;

    @Autowired
    private PmsValidate pmsValidate;

    @Autowired
    private SkuImportEsSupport skuImportEsSupport;

    @Override
    public BaseResult<Long> processor(AuditRequestDTO requestDTO) {
        SkuAuditDO skuAuditDO = pmsValidate.validateAudit(requestDTO.getAuditId());
        if (YNEnum.Y.getCode().equals(requestDTO.getResult())) {
            skuAuditDO.setStatus(SkuAuditStatusEnum.AUDIT_PASS.getCode());
        } else {
            skuAuditDO.setStatus(SkuAuditStatusEnum.AUDIT_NOT_PASS.getCode());
        }
        skuAuditDO.setRemark(requestDTO.getRemark());
        skuAuditMapper.updateById(skuAuditDO);
        if (YNEnum.Y.getCode().equals(requestDTO.getResult())) {
            skuImportEsSupport.sendSyncSkuMq(skuAuditDO.getSkuId());
        }
        return ResultUtil.success(skuAuditDO.getId());
    }


}
