package com.dmall.search.api.service;

import com.dmall.common.dto.BaseResult;
import com.dmall.search.api.dto.SearchRequestDTO;
import com.dmall.search.api.dto.SearchResponseDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description: 商品搜索服务
 * @author: created by hang.yu on 2020/3/5 21:54
 */
public interface SkuSearchService {

    /**
     * 导入所有sku数据
     */
    @GetMapping("/page")
    @ApiOperation(value = "搜索sku")
    BaseResult<SearchResponseDTO> skuSearch(SearchRequestDTO requestDTO);
}
