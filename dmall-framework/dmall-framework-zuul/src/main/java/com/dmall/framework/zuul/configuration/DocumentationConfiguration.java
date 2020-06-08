package com.dmall.framework.zuul.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * SwaggerResourcesProvider 是资源提供者，我们重写他，把各个微服务的API文档资源路径返回，注释部分为手动添加的方式
 */
@Slf4j
@Component
@Primary
@EnableConfigurationProperties({ZuulSwaggerProperties.class, WhiteListProperties.class})
public class DocumentationConfiguration implements SwaggerResourcesProvider {

    private final RouteLocator routeLocator;

    @Autowired
    private ZuulSwaggerProperties zuulSwaggerProperties;

    public DocumentationConfiguration(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();

        List<Route> routes = routeLocator.getRoutes();
        List<String> ignoreProjects = zuulSwaggerProperties.getIgnoreProjects();

        routes.forEach(route -> {
            if (!CollectionUtils.isEmpty(ignoreProjects) && !ignoreProjects.contains(route.getId())) {
                resources.add(swaggerResource(route.getId(), route.getFullPath()
                    .replace("**", "v2/api-docs")));
            }
        });
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(zuulSwaggerProperties.getVersion());
        return swaggerResource;
    }
}