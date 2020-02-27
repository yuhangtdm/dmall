package com.dmall.sso.service.impl.multiple.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @description: 选择数据源切面
 * @author: created by hang.yu on 2020/2/25 20:31
 */
@Component
@Slf4j
@Aspect
@Order(-1)
public class DataSourceAspect {

  /*  @Pointcut("execution(public *  com.dmall.sso.service.impl.admin.*.*(..))")
    public void admin() {
    }

    @Pointcut("execution(public *  com.dmall.sso.service.impl.portal.*.*(..))")
    public void portal() {
    }

    @Before("admin()")
    public void doBeforeAdmin() {
        DataSourceContextHolder.setDataSource(DataSourceEnum.ADMIN.name());
    }

    @Before("portal()")
    public void doBeforePortal() {
        DataSourceContextHolder.setDataSource(DataSourceEnum.PORTAL.name());
    }

    @After("admin() && portal()" )
    public void doAfter() {
        DataSourceContextHolder.clear();
    }*/
}