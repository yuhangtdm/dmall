package com.dmall.search.service.impl;

import com.dmall.common.dto.BaseResult;
import com.dmall.search.api.dto.SearchRequestDTO;
import com.dmall.search.api.dto.SearchResponseDTO;
import com.dmall.search.api.service.SkuSearchService;
import com.dmall.search.service.impl.handler.SkuSearchHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 搜索服务实现类
 * @author: created by hang.yu on 2020/3/5 21:59
 */
@RestController
public class SkuSearchServiceImpl implements SkuSearchService {

    @Autowired
    private SkuSearchHandler skuSearchHandler;

    @Override
    public BaseResult<SearchResponseDTO> skuSearch(SearchRequestDTO requestDTO) {
        return skuSearchHandler.handler(requestDTO);
    }
}
