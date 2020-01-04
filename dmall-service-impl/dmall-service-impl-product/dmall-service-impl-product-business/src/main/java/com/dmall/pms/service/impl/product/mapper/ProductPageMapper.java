package com.dmall.pms.service.impl.product.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.pms.api.dto.product.request.PageProductRequestDTO;
import com.dmall.pms.generator.dataobject.ProductDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: AttributePageMapper
 * @author: created by hang.yu on 2019/12/24 23:29
 */
public interface ProductPageMapper {

    List<ProductDO> productPage(Page page, @Param("request") PageProductRequestDTO requestDTO);
}
