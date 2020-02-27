package com.dmall.sso.service.impl.multiple.datasource;

import java.lang.annotation.*;

/**
 * @description: 数据源注解
 * @author: created by hang.yu on 2020/2/25 20:29
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SelectDataSource {
    DataSourceEnum value() default DataSourceEnum.ADMIN;
}
