package com.dmall.sso.service.impl.multiple.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 多数据源配置(弃用)
 * @author: created by hang.yu on 2020/2/25 20:35
 */
// @Configuration
public class MyBatisConfiguration {

    @Bean(name = "db1")
    @ConfigurationProperties(prefix = "spring.datasource.druid.admin")
    public DruidDataSource db1() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "db2")
    @ConfigurationProperties(prefix = "spring.datasource.druid.portal")
    public DruidDataSource db2() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源配置
     */
    @Bean
    @Primary
    public DataSource multipleDataSource(@Qualifier("db1") DataSource admin,
        @Qualifier("db2") DataSource portal) {
        MultipleDataSource multipleDataSource = new MultipleDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.ADMIN.name(), admin);
        targetDataSources.put(DataSourceEnum.PORTAL.name(), portal);
        // 添加数据源
        multipleDataSource.setTargetDataSources(targetDataSources);
        // 设置默认数据源
        multipleDataSource.setDefaultTargetDataSource(admin);
        return multipleDataSource;
    }

}
