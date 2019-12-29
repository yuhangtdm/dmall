package com.dmall.pms.service.impl.sku.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.pms.api.dto.sku.request.PageSkuRequestDTO;
import com.dmall.pms.api.dto.sku.response.PageSkuResponseDTO;

import java.util.List;

/**
 * @description: SkuPageMapper
 * @author: created by hang.yu on 2019/12/29 17:24
 */
public interface SkuPageMapper {

    List<PageSkuResponseDTO> skuPage(Page page, PageSkuRequestDTO requestDTO);
}
