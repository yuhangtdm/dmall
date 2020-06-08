package com.dmall.bms.service.impl.menu.handler;

import com.dmall.common.model.swagger.SwaggerInfo;
import com.dmall.common.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: created by hang.yu on 2020/5/16 16:48
 */
public class PermissionTest extends BaseTest {
    private static final String URL = "http://dmall-framework-zuul:7010/dmall-service-impl-background/v2/api-docs";

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity(URL, String.class);
        String body = forEntity.getBody();
        SwaggerInfo swagger = JsonUtil.fromJson(body, new TypeReference<SwaggerInfo>() {});
        System.out.println(swagger);
    }
}
