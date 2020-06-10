package com.dmall.pms.service.impl.brand.cache;

import com.dmall.pms.generator.dataobject.BrandDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dmall.pms.ProductApplication;

import java.util.List;

/**
 * @description:
 * @author: created by hang.yu on 2020/6/10 23:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)
public class BrandCacheServiceTest {

    @Autowired
    private BrandCacheService brandCacheService;

    @Test
    public void selectAll() {
        long s = System.currentTimeMillis();
        List<BrandDO> brandDOS =
                brandCacheService.selectAll();
        System.out.println(System.currentTimeMillis() -s );
        System.out.println(brandDOS.size());
    }
}