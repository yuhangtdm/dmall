package com.dmall.component.web.configuration;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.enums.BasicStatusEnum;
import com.dmall.common.enums.component.WebErrorEnum;
import com.dmall.common.enums.error.ErrorCodeEnum;
import com.dmall.common.model.BasicConfiguration;
import com.dmall.common.model.exception.ComponentException;
import com.dmall.common.util.JsonUtil;
import com.dmall.component.web.properties.DMallSwaggerProperties;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * @description: Swagger配置
 * 访问地址(swagger原生页面)：http://项目实际地址/swagger-ui.html
 * 访问地址(bootstrap美化页面)：http://项目实际地址/doc.html
 * @author: created by hang.yu on 2019/10/16 22:28
 */
@Slf4j
@Configuration
@EnableSwagger2
@ConditionalOnMissingBean(value = DMallSwaggerConfiguration.class)
@EnableConfigurationProperties({DMallSwaggerProperties.class})
@ConditionalOnProperty(prefix = "dmall.web.swagger", value = "enabled", havingValue = "true")
public class DMallSwaggerConfiguration implements BasicConfiguration {

    @Autowired
    private DMallSwaggerProperties dMallSwaggerProperties;

    public static final List<ErrorCodeEnum> errorEnums = Lists.newArrayList();

    static {
        errorEnums.addAll(Arrays.asList(BasicStatusEnum.values()));
    }

    @Override
    @PostConstruct
    public void check() {
        log.info("init -> [{}],properties:\n{}", "DMallSwaggerProperties", JsonUtil.toJsonPretty(dMallSwaggerProperties));
        if (dMallSwaggerProperties.getEnabled() && StrUtil.isBlank(dMallSwaggerProperties.getBasePackage())) {
            throw new ComponentException(WebErrorEnum.NO_BASE_PACKAGE);
        }
    }

    /**
     * 创建API应用
     */
    @Bean
    public Docket createRestApi() {
        List<ResponseMessage> responseMessageList = Lists.newArrayList();
        for (ErrorCodeEnum errorEnum : errorEnums) {
            try {
                responseMessageList.add(new ResponseMessageBuilder().code(Integer.parseInt(errorEnum.getCode()))
                        .message(errorEnum.getMsg()).build());
            } catch (Exception e) {
                log.error("status not valid,{}", errorEnum);
            }
        }
        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(dMallSwaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                ;
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(dMallSwaggerProperties.getTitle())
                .description(dMallSwaggerProperties.getDescription())
                .termsOfServiceUrl(dMallSwaggerProperties.getServiceUrl())
                .version(dMallSwaggerProperties.getVersion())
                .contact(new Contact(dMallSwaggerProperties.getContactName(), dMallSwaggerProperties.getContactUrl(), dMallSwaggerProperties.getContactEmail()))
                .build();
    }

}