package com.dmall.pms.service.impl.sku.handler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.enums.YNEnum;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.util.ResultUtil;
import com.dmall.pms.api.enums.SkuAuditStatusEnum;
import com.dmall.pms.api.dto.sku.request.PageSkuRequestDTO;
import com.dmall.pms.api.dto.sku.response.PageSkuResponseDTO;
import com.dmall.pms.service.impl.sku.mapper.SkuPageMapper;
import com.dmall.pms.service.impl.sku.mapper.SkuPageVO;
import com.dmall.pms.service.support.SkuStockSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: sku分页处理器
 * @author: created by hang.yu on 2019-12-16 15:14:50
 */
@Component
public class PageSkuHandler extends AbstractCommonHandler<PageSkuRequestDTO, SkuPageVO, PageSkuResponseDTO> {

    @Autowired
    private SkuPageMapper skuPageMapper;

    @Autowired
    private SkuStockSupport skuStockSupport;

    @Override
    public BaseResult<ResponsePage<PageSkuResponseDTO>> processor(PageSkuRequestDTO requestDTO) {
        Page<PageSkuResponseDTO> page = new Page(requestDTO.getCurrent(), requestDTO.getSize());
        List<PageSkuResponseDTO> skuList = skuPageMapper.skuPage(page, requestDTO).stream()
                .map(skuPageVO -> doConvertDto(skuPageVO, PageSkuResponseDTO.class))
                .collect(Collectors.toList());
        return ResultUtil.success(new ResponsePage<>(page.getTotal(), skuList));
    }

    @Override
    protected void customerConvertDto(PageSkuResponseDTO result, SkuPageVO doo) {
        result.setRecommendStatus(EnumUtil.getCodeDescEnum(YNEnum.class, doo.getRecommendStatus()));
        result.setNewStatus(EnumUtil.getCodeDescEnum(YNEnum.class, doo.getNewStatus()));
        result.setPreviewStatus(EnumUtil.getCodeDescEnum(YNEnum.class, doo.getPreviewStatus()));
        result.setAuditStatus(EnumUtil.getCodeDescEnum(SkuAuditStatusEnum.class, doo.getAuditStatus()));
        result.setPublishStatus(EnumUtil.getCodeDescEnum(YNEnum.class, doo.getPublishStatus()));
        result.setSalableStock(skuStockSupport.getSaleableStock(doo.getId()));
        result.setLowStock(result.getStock() - result.getSalableStock());
    }
}
