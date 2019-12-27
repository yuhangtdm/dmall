package com.dmall.pms.service.impl.product.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.pms.api.dto.product.request.PageProductRequestDTO;
import com.dmall.pms.api.dto.product.response.PageProductResponseDTO;

import java.util.List;

/**
 * @description: AttributePageMapper
 * @author: created by hang.yu on 2019/12/24 23:29
 */
public interface ProductPageMapper {

    List<PageProductResponseDTO> productPage(Page page, PageProductRequestDTO requestDTO);
}
