package com.dmall.component.activemq;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.dmall.common.model.BasicConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @description: ActiveMq配置类
 * @author: created by hang.yu on 2020/3/9 22:39
 */
@Slf4j
@EnableJms
@Configuration
@EnableConfigurationProperties(DMallActiveMQProperties.class)
public class ActiveMqConfiguration implements BasicConfiguration {

    @Autowired
    private DMallActiveMQProperties dMallActiveMQProperties;

    /**
     * 配置topic
     */
    @Bean
    @ConditionalOnProperty(prefix = "dmall.activemq", value = "useTopic", havingValue = "true")
    public Topic defaultTopic() {
        return new ActiveMQTopic(dMallActiveMQProperties.getDefaultTopic());
    }

    @Bean
    @ConditionalOnProperty(prefix = "dmall.activemq", value = "useQueue", havingValue = "true")
    public Queue queue() {
        return new ActiveMQQueue(dMallActiveMQProperties.getDefaultQueue());
    }

    /**
     * springboot默认只配置queue类型消息，如果要使用topic类型的消息，则需要配置该bean
     */
    @Bean
    public DefaultJmsListenerContainerFactory jmsTopicListenerContainerFactory(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(false);
        return factory;
    }

    @Override
    @PostConstruct
    public void check() {
        log.info("init -> [{}],properties:\n{}", "DMallActiveMQProperties", JSON.toJSONString(dMallActiveMQProperties, true));
        if (dMallActiveMQProperties.getEnabled()) {
            if (dMallActiveMQProperties.getUseTopic() && StrUtil.isBlank(dMallActiveMQProperties.getDefaultTopic())) {
                throw new IllegalArgumentException("invalid activemq config");
            }
            if (dMallActiveMQProperties.getUseQueue() && StrUtil.isBlank(dMallActiveMQProperties.getDefaultQueue())) {
                throw new IllegalArgumentException("invalid activemq config");
            }
        }
    }
}
