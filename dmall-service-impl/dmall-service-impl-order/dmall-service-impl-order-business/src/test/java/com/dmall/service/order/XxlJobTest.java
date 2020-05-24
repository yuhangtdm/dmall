package com.dmall.service.order;

import com.dmall.common.model.xxljob.XxlJobGroup;
import com.dmall.component.web.util.HttpClientUtil;
import com.dmall.oms.OrderApplication;
import com.google.common.collect.Maps;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @description:
 * @author: created by hang.yu on 2020/3/21 22:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class XxlJobTest {

    private static final String url = "http://192.168.38.162:8080/xxl-job-admin/jobgroup/save";

    private static final String url2 = "http://localhost:8080/xxl-job-admin/jobgroup/add";


    public static void main(String[] args) {
        test();
    }

    public static void test() {

        Map<String, String> map = Maps.newHashMap();
        map.put("appname", "test");
        map.put("title", "test自动新增");
        map.put("order", "1");
        map.put("addressType", "0");
        String post = HttpClientUtil.send(url2, map);
        System.out.println(post);

    }

}
