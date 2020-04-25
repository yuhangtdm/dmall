package com.dmall.pms.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.pms.api.dto.skuaudit.request.ListSkuAuditRequestDTO;
import com.dmall.pms.api.dto.skuaudit.request.PageSkuAuditRequestDTO;
import com.dmall.pms.api.dto.skuaudit.request.AuditRequestDTO;
import com.dmall.pms.api.dto.skuaudit.response.SkuAuditResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: sku审核记录服务
 * @author: created by hang.yu on 2020-04-25 14:49:35
 */
@Api(tags = "sku审核记录服务")
@RequestMapping("/skuAudit")
public interface SkuAuditService {

    @PostMapping
    @ApiOperation(value = "审核")
    BaseResult<Long> audit(@Valid @RequestBody AuditRequestDTO requestDTO);

    @PostMapping("/list")
    @ApiOperation(value = "sku审核记录列表")
    BaseResult<List<SkuAuditResponseDTO>> list(@RequestBody ListSkuAuditRequestDTO requestDTO);

    @PostMapping("/page")
    @ApiOperation(value = "sku审核记录列表")
    BaseResult<ResponsePage<SkuAuditResponseDTO>> page(@RequestBody PageSkuAuditRequestDTO requestDTO);
}
