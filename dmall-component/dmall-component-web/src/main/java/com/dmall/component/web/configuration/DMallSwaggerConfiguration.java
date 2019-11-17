package com.dmall.component.web.configuration;

import com.alibaba.fastjson.JSON;
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
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
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
 * @author: created by yuhang on 2019/10/16 22:28
 */
@Slf4j
@Configuration
@EnableSwagger2
@EnableConfigurationProperties({DMallSwaggerProperties.class})
@ConditionalOnProperty(prefix = "dmall.web.swagger", value = "enabled", havingValue = "true")
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
        /*for (HttpStatus httpStatus : HttpStatus.values()) {
            responseMessageList.add(new ResponseMessageBuilder().code(httpStatus.value()).message(httpStatus.getReasonPhrase()).build());
        }*/
        return new Docket(DocumentationType.SWAGGER_2)
//                .globalResponseMessage(RequestMethod.GET, responseMessageList)
//                .globalResponseMessage(RequestMethod.POST, responseMessageList)
//                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
//                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
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

    @Override
    public void check() {
        log.info("init -> [{}],properties:\n{}", "DMallSwaggerProperties", JSON.toJSONString(dMallSwaggerProperties, true));
        if (dMallSwaggerProperties.getEnabled() && StringUtils.isBlank(dMallSwaggerProperties.getBasePackage())){
            throw new WebException(WebErrorEnum.NO_BASE_PACKAGE);
        }
    }
}