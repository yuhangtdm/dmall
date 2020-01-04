package com.dmall.component.elasticsearch.configuration;

import com.alibaba.fastjson.JSON;
import com.dmall.common.model.configuration.BasicConfiguration;
import com.dmall.component.elasticsearch.ESDao;
import com.dmall.component.elasticsearch.properties.DMallElasticSearchProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: ElasticSearch配置类
 * @author: created by hang.yu on 2019/11/6 22:58
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(DMallElasticSearchProperties.class)
public class DMallElasticSearchConfiguration implements BasicConfiguration {

    @Autowired
    private DMallElasticSearchProperties dMallElasticSearchProperties;

    @Bean
    public ESDao esDao() {
        return new ESDao();
    }

    @Override
    public void check() {
        log.info("init -> [{}],properties:\n{}", "DMallElasticSearchProperties", JSON.toJSONString(dMallElasticSearchProperties, true));
    }
}
