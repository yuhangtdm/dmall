package com.dmall.component.mybatisplus.generator;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: GeneratorCodeUtil
 * @author: created by hang.yu on 2019/12/1 14:34
 */
@Slf4j
public class GeneratorCodeUtil {

    public static void main(String[] args) {
        /**
         * 表名不传则生成所有表的
         */
        generateByTables(Constants.GENERATOR_MODULE_NAME,"oms_sub_order");
    }

    /**
     * 通过表名生成相关类"
     */
    private static void generateByTables(String module, String... tableNames) {
        moduleGenerator(module, tableNames);
    }

    /**
     * 生成文件的主方法
     */
    private static void moduleGenerator(String module, String[] tableNames) {

        // 全局配置
        GlobalConfig globalConfig = getGlobalConfig(module);
        // 数据源配置
        DataSourceConfig dataSourceConfig = getDataSourceConfig();
        // 包配置
        PackageConfig packageConfig = getPackageConfig();
        // 策略配置
        StrategyConfig strategyConfig = getStrategyConfig(tableNames);
        // 模板配置
        TemplateConfig templateConfig = getTemplateConfig();
        // 采用自定义代码生成器来完成
        new DMallAutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setPackageInfo(packageConfig)
                .setStrategy(strategyConfig)
                .setTemplate(templateConfig)
                .setTemplateEngine(new DMallVelocityTemplateEngine())
                .execute();

    }

    /**
     * 全局配置，配置生成文件的目录
     */
    private static GlobalConfig getGlobalConfig(String module) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOpen(false)
                .setOutputDir(System.getProperty("user.dir") + module + Constants.SRC_MAIN_JAVA)//生成文件的输出目录
                .setFileOverride(true)//是否覆盖已有文件
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setActiveRecord(false)
                .setIdType(IdType.AUTO)
                .setAuthor(Constants.AUTHOR)
                .setDateType(DateType.ONLY_DATE)
                .setEntityName(Constants.ENTITY_name)
                .setServiceImplName(Constants.SERVICE_NAME);
        return globalConfig;
    }

    /**
     * 配置数据源
     */
    private static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setDriverName(Constants.DRIVER_NAME)
                .setUsername(Constants.USERNAME)
                .setPassword(Constants.PASSWORD)
                .setUrl(Constants.DB_URL);
        return dataSourceConfig;
    }

    /**
     * 配置生成包名
     */
    private static PackageConfig getPackageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setEntity(Constants.ENTITY_PACKAGE_NAME)
                .setParent(Constants.PACKAGE_PARENT_NAME)
                .setModuleName(Constants.PACKAGE_MODULE_NAME + StringPool.DOT + Constants.PACKAGE_GENERATOR_NAME);
        return packageConfig;
    }

    /**
     * 定义策略
     */
    private static StrategyConfig getStrategyConfig(String... tableNames) {
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setEntityLombokModel(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix(Constants.PACKAGE_MODULE_NAME + StrUtil.UNDERLINE);
        if (tableNames != null) {
            strategyConfig.setInclude(tableNames);
        }

        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill(Constants.GMT_CREATED, FieldFill.INSERT));
        tableFillList.add(new TableFill(Constants.GMT_MODIFIED, FieldFill.INSERT_UPDATE));
        tableFillList.add(new TableFill(Constants.IS_DELETED, FieldFill.INSERT));
        tableFillList.add(new TableFill(Constants.CREATOR, FieldFill.INSERT));
        tableFillList.add(new TableFill(Constants.MODIFIER, FieldFill.INSERT_UPDATE));
        strategyConfig.setTableFillList(tableFillList);
        strategyConfig.setLogicDeleteFieldName(Constants.IS_DELETED);
        return strategyConfig;
    }

    /**
     * 自定义代码生成模板, 不生成controller
     * 在模版引擎的执行方法中会校验如果模版为空则不会执行writer()方法
     */
    private static TemplateConfig getTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity(Constants.TEMPLATES_ENTITY) // entity模板采用自定义模板
                .setMapper(Constants.TEMPLATES_MAPPER)// mapper模板采用自定义模板
                .setXml(null) // xml采用自定义模板
                .setService(Constants.TEMPLATES_SERVICE) // service接口采用自定义模板
                .setServiceImpl(Constants.TEMPLATES_SERVICE_IMPL) // serviceImpl模板采用自定义模板
                .setController(null); // 不生成controller
        return templateConfig;

    }
}