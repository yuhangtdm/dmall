package com.dmall.search.service.impl.handler;

import com.alibaba.fastjson.JSON;
import com.dmall.common.dto.BaseResult;
import com.dmall.search.SearchApplication;
import com.dmall.search.api.dto.SearchRequestDTO;
import com.dmall.search.api.dto.SearchResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: created by hang.yu on 2020/3/8 13:21
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchApplication.class)
public class SkuSearchHandlerTest {

    @Autowired
    private SkuSearchHandler skuSearchHandler;

    @Test
    public void processor() {
        SearchRequestDTO searchRequestDTO = new SearchRequestDTO();
        searchRequestDTO.setCat(3L);
//        searchRequestDTO.setBra("1");
//        searchRequestDTO.setKeyword("小米手机");
//        searchRequestDTO.setEv("30000,30079");
//        searchRequestDTO.setJl("");
//        searchRequestDTO.setSort("");
//        searchRequestDTO.setFrom(0);
//        searchRequestDTO.setSize(20);

        BaseResult<SearchResponseDTO> processor =
                skuSearchHandler.processor(searchRequestDTO);

        log.info("获取结果:\n{}", JSON.toJSONString(processor, true));
    }
}