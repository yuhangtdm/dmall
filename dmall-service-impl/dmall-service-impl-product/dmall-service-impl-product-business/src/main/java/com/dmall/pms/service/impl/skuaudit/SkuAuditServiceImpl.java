package com.dmall.pms.service.impl.skuaudit;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.pms.api.dto.skuaudit.request.ListSkuAuditRequestDTO;
import com.dmall.pms.api.dto.skuaudit.request.PageSkuAuditRequestDTO;
import com.dmall.pms.api.dto.skuaudit.request.AuditRequestDTO;
import com.dmall.pms.api.dto.skuaudit.response.SkuAuditResponseDTO;
import com.dmall.pms.api.service.SkuAuditService;
import com.dmall.pms.service.impl.skuaudit.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: sku审核记录服务实现
 * @author: created by hang.yu on 2020-04-25 14:49:35
 */
@RestController
public class SkuAuditServiceImpl implements SkuAuditService {

    @Autowired
    private AuditHandler auditHandler;

    @Autowired
    private ListSkuAuditHandler listSkuAuditHandler;

    @Autowired
    private PageSkuAuditHandler pageSkuAuditHandler;

    @Override
    public BaseResult<Long> audit(@RequestBody AuditRequestDTO requestDTO) {
        return auditHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<List<SkuAuditResponseDTO>> list(@RequestBody ListSkuAuditRequestDTO requestDTO) {
        return listSkuAuditHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<ResponsePage<SkuAuditResponseDTO>> page(PageSkuAuditRequestDTO requestDTO) {
        return pageSkuAuditHandler.handler(requestDTO);
    }

}
