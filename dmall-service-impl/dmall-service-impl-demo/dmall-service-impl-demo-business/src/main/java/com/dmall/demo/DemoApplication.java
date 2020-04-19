package com.dmall.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: created by hang.yu on 2020/4/19 20:05
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.dmall.oms.generator.mapper", "com.dmall.oms.service.impl"})
public class DemoApplication {
}
