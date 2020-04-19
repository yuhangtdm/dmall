package com.dmall.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: DemoApplication
 * @author: created by hang.yu on 2020/4/19 20:05
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.dmall.demo.generator.mapper"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
