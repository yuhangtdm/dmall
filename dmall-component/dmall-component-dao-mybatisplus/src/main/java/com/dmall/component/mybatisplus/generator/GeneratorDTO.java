package com.dmall.component.mybatisplus.generator;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description: 生成代码的自定义请求参数
 * @author: created by hang.yu on 2020/4/19 17:34
 */
@Data
@Accessors(chain = true)
public class GeneratorDTO {

    /**
     * 模块名称
     */
    private String module;

    /**
     * 业务名称
     */
    private String business;

    /**
     * 数据库地址 默认值 本地
     */
    private String databaseUrl = "localhost:3306/dmall_{}";

    /**
     * 数据库用户名
     */
    private String databaseUserName = "root";

    /**
     * 数据库密码
     */
    private String databasePassword = "root";

    /**
     * 表名
     */
    private String[] tableNames;

    /**
     * 是否生成自定义的文件
     */
    private boolean generateCustomer;

}
