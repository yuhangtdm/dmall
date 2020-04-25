package com.dmall.pms.service.impl.skuaudit.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.dto.skuaudit.request.PageSkuAuditRequestDTO;
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
public class PageSkuAuditHandler extends AbstractCommonHandler<PageSkuAuditRequestDTO, SkuAuditDO, SkuAuditResponseDTO> {

    @Autowired
    private SkuAuditMapper skuAuditMapper;

    @Override
    public BaseResult<ResponsePage<SkuAuditResponseDTO>> processor(PageSkuAuditRequestDTO requestDTO) {
        LambdaQueryWrapper<SkuAuditDO> wrapper = Wrappers.<SkuAuditDO>lambdaQuery()
                .eq(requestDTO.getProductId() != null, SkuAuditDO::getProductId, requestDTO.getProductId())
                .eq(requestDTO.getSkuId() != null, SkuAuditDO::getSkuId, requestDTO.getSkuId())
                .eq(requestDTO.getStatus() != null, SkuAuditDO::getStatus, requestDTO.getStatus());
        IPage<SkuAuditDO> page = new Page<>(requestDTO.getCurrent(), requestDTO.getSize());
        page = skuAuditMapper.selectPage(page, wrapper);
        List<SkuAuditResponseDTO> list = page.getRecords().stream().map(skuAuditDO -> doConvertDto(skuAuditDO, SkuAuditResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(new ResponsePage(page.getTotal(), list));
    }

}
