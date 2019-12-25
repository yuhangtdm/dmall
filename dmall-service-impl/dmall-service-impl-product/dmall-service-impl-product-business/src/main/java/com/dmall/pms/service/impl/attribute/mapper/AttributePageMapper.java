package com.dmall.pms.service.impl.attribute.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.pms.api.dto.attribute.request.PageAttributeRequestDTO;
import com.dmall.pms.api.dto.attribute.response.PageAttributeResponseDTO;

import java.util.List;

/**
 * @description: AttributePageMapper
 * @author: created by hang.yu on 2019/12/24 23:29
 */
public interface AttributePageMapper {

    List<PageAttributeResponseDTO> pageAttribute(Page page, PageAttributeRequestDTO requestDTO);
}
