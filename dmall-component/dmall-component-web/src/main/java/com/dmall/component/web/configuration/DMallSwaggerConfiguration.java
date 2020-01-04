package com.dmall.component.web.configuration;

import com.alibaba.fastjson.JSON;
import com.dmall.common.enums.base.BasicStatusEnum;
import com.dmall.common.model.configuration.BasicConfiguration;
import com.dmall.component.web.exception.WebErrorEnum;
import com.dmall.component.web.exception.WebException;
import com.dmall.component.web.properties.DMallSwaggerProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.ArrayList;
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
@EnableConfigurationProperties({DMallSwaggerProperties.class})
@ConditionalOnProperty(prefix = "dmall.web.swagger" , value = "enabled" , havingValue = "true" )
public class DMallSwaggerConfiguration implements BasicConfiguration {

    @Autowired
    private DMallSwaggerProperties dMallSwaggerProperties;

    /**
     * 创建API应用
     */
    @Bean
    public Docket createRestApi() {
        /**
         * 配置响应状态码
         */
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        for (BasicStatusEnum value : BasicStatusEnum.values()) {
            responseMessageList.add(new ResponseMessageBuilder().code(Integer.valueOf(value.getCode()))
                    .message(value.getMsg()).build());
        }
        customerStatus(responseMessageList);

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

    protected void customerStatus(List<ResponseMessage> responseMessageList) {

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

    @Override
    public void check() {
        log.info("init -> [{}],properties:\n{}" , "DMallSwaggerProperties" , JSON.toJSONString(dMallSwaggerProperties, true));
        if (dMallSwaggerProperties.getEnabled() && StringUtils.isBlank(dMallSwaggerProperties.getBasePackage())) {
            throw new WebException(WebErrorEnum.NO_BASE_PACKAGE);
        }
    }
}