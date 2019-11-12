package com.dmall.component.web.configuration;

import com.dmall.common.constants.component.web.WebConstants;
import com.dmall.component.web.log.LogAdvice;
import com.dmall.component.web.properties.DMallLogProperties;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: created by yuhang on 2019/11/10 18:47
 */
@Configuration
@EnableConfigurationProperties({DMallLogProperties.class})
@ConditionalOnProperty(prefix = "dmall.web.log", value = "enabled", havingValue = "true")
public class DMallLogAspectConfiguration {

    @Autowired
    private DMallLogProperties dMallLogProperties;

    @Bean
    public AspectJExpressionPointcutAdvisor configurabledvisor() {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(public * "+dMallLogProperties.getPointcut()+"..*(..))");
        advisor.setAdvice(new LogAdvice());
        return advisor;
    }
}
