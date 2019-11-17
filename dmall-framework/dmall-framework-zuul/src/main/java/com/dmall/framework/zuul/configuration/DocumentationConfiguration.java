package com.dmall.framework.zuul.configuration;

import com.dmall.component.web.properties.DMallSwaggerProperties;
import lombok.extern.slf4j.Slf4j;
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
public class DocumentationConfiguration implements SwaggerResourcesProvider {
    private final RouteLocator routeLocator;

    private final DMallSwaggerProperties DMallSwaggerProperties;

    public DocumentationConfiguration(RouteLocator routeLocator, DMallSwaggerProperties DMallSwaggerProperties) {
        log.info("swagger启动成功");
        this.routeLocator = routeLocator;
        this.DMallSwaggerProperties = DMallSwaggerProperties;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();

        List<Route> routes = routeLocator.getRoutes();
        List<String> ignoreProjects = DMallSwaggerProperties.getIgnoreProjects();

        routes.forEach(route -> {
            if (!CollectionUtils.isEmpty(ignoreProjects) && !ignoreProjects.contains(route.getId())){
                resources.add(swaggerResource(route.getId(), route.getFullPath()
                        .replace("**", "v2/api-docs"), "1.0"));
            }
        });
        return resources;
    }


    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}