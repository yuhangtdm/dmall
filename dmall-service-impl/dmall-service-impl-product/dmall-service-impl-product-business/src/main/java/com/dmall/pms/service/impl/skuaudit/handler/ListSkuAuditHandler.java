package com.dmall.pms.service.impl.skuaudit.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.skuaudit.request.ListSkuAuditRequestDTO;
import com.dmall.pms.api.dto.skuaudit.response.SkuAuditResponseDTO;
import com.dmall.pms.generator.dataobject.SkuAuditDO;
import com.dmall.pms.generator.mapper.SkuAuditMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: sku审核记录列表处理器
 * @author: created by hang.yu on 2020-04-25 14:49:35
 */
@Component
public class ListSkuAuditHandler
    extends AbstractCommonHandler<ListSkuAuditRequestDTO, SkuAuditDO, SkuAuditResponseDTO> {

    @Autowired
    private SkuAuditMapper skuAuditMapper;

    @Override
    public BaseResult<List<SkuAuditResponseDTO>> processor(ListSkuAuditRequestDTO requestDTO) {
        List<SkuAuditResponseDTO> skuList = skuAuditMapper.selectList(Wrappers.<SkuAuditDO>lambdaQuery()
            .eq(requestDTO.getProductId() != null, SkuAuditDO::getProductId, requestDTO.getProductId())
            .eq(requestDTO.getSkuId() != null, SkuAuditDO::getSkuId, requestDTO.getSkuId())
            .eq(requestDTO.getStatus() != null, SkuAuditDO::getStatus, requestDTO.getStatus())).stream()
            .map(skuAuditDO -> doConvertDto(skuAuditDO, SkuAuditResponseDTO.class)).collect(Collectors.toList());
        return ResultUtil.success(skuList);
    }

}
