package com.dmall.pms.service.impl.sku.handler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.common.enums.base.EnumUtil;
import com.dmall.common.enums.base.YNEnum;
import com.dmall.common.model.handler.AbstractCommonHandler;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import com.dmall.component.web.util.ResultUtil;
import com.dmall.pms.api.dto.sku.enums.SkuAuditStatusEnum;
import com.dmall.pms.api.dto.sku.request.PageSkuRequestDTO;
import com.dmall.pms.api.dto.sku.response.PageSkuResponseDTO;
import com.dmall.pms.generator.dataobject.SkuDO;
import com.dmall.pms.service.impl.product.common.ProductValidate;
import com.dmall.pms.service.impl.sku.mapper.SkuPageMapper;
import com.dmall.pms.service.impl.sku.mapper.SkuPageVO;
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

    @Override
    public BaseResult<LayuiPage<PageSkuResponseDTO>> processor(PageSkuRequestDTO requestDTO) {
        Page<PageSkuResponseDTO> page = new Page(requestDTO.getCurrent(), requestDTO.getSize());
        List<PageSkuResponseDTO> skuList = skuPageMapper.skuPage(page, requestDTO).stream()
                .map(skuPageVO -> doConvertDto(skuPageVO, PageSkuResponseDTO.class)).collect(Collectors.toList());
        return ResultUtil.success(new LayuiPage<>(page.getTotal(), skuList));
    }

    @Override
    protected void customerConvertDto(PageSkuResponseDTO result, SkuPageVO doo) {
        result.setRecommendStatus(EnumUtil.getKeyValueEnum(YNEnum.class, doo.getRecommendStatus()));
        result.setNewStatus(EnumUtil.getKeyValueEnum(YNEnum.class, doo.getNewStatus()));
        result.setPreviewStatus(EnumUtil.getKeyValueEnum(YNEnum.class, doo.getPreviewStatus()));
        result.setAuditStatus(EnumUtil.getKeyValueEnum(SkuAuditStatusEnum.class, doo.getAuditStatus()));
        result.setPublishStatus(EnumUtil.getKeyValueEnum(YNEnum.class, doo.getPublishStatus()));
    }
}
