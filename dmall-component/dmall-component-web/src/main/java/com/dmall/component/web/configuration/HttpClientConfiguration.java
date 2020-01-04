package com.dmall.component.web.configuration;

import com.dmall.component.web.properties.HttpClientProperties;
import com.dmall.component.web.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.IdleConnectionEvictor;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/17 15:08
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({HttpClientProperties.class})
@ConditionalOnProperty(prefix = "dmall.web.httpclient", value = "enabled", havingValue = "true")
public class HttpClientConfiguration {

    @Autowired
    private HttpClientProperties httpClientProperties;

    /**
     * 连接池管理器
     */
    @Bean
    public PoolingHttpClientConnectionManager cm() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // 最大连接数
        cm.setMaxTotal(httpClientProperties.getMaxTotal());
        // 连接主机最大并发数
        cm.setDefaultMaxPerRoute(httpClientProperties.getDefaultMaxPerRoute());
        return cm;
    }

    /**
     * httpclient构建器
     */
    @Bean
    public HttpClientBuilder builder() {
        return HttpClientBuilder.create().
                setConnectionManager(cm());
    }

    /**
     * httpclient 多例
     */
    @Bean
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CloseableHttpClient httpClient() {
        return builder().build();
    }

    /**
     * requestConfig构建器
     **/
    @Bean
    public RequestConfig requestConfigBuilder() {
        RequestConfig.Builder builder = RequestConfig.custom();
        builder.setConnectTimeout(httpClientProperties.getConnectTimeout());
        builder.setConnectionRequestTimeout(httpClientProperties.getConnectionRequestTimeout());
        builder.setSocketTimeout(httpClientProperties.getSocketTimeout());
        return builder.build();
    }

    /**
     * 线程监控清理无效连接
     **/
    @Bean(destroyMethod = "shutdown")
    public IdleConnectionEvictor idleConnectionEvictor() {
        return new IdleConnectionEvictor(cm(), httpClientProperties.getMaxIdleTime(), httpClientProperties.getTimeUnit());
    }

    @Bean
    public HttpClientUtil httpClientUtil() {
        return new HttpClientUtil(httpClient());
    }
}
