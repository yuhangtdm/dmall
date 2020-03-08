package com.dmall.search.service.impl.handler;

import com.dmall.search.SearchApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @description:
 * @author: created by hang.yu on 2020/3/5 23:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchApplication.class)
public class ImportSkuToEsHandlerTest {

    @Autowired
    private ImportSkuToEsHandler importSkuToEsHandler;
    @Test
    public void importAllSku() {
        importSkuToEsHandler.importAllSku();
    }
}