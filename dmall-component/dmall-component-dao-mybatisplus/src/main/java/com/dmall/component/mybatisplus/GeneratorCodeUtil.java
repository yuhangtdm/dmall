package com.dmall.component.mybatisplus;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.dmall.component.mybatisplus.generator.DMallAutoGenerator;
import com.dmall.component.mybatisplus.generator.DMallVelocityTemplateEngine;
import com.dmall.component.mybatisplus.generator.GeneratorDTO;
import com.dmall.component.mybatisplus.generator.MybatisPlusConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 生成代码工具类
 * @author: created by hang.yu on 2019/12/1 14:34
 */
@Slf4j
public class GeneratorCodeUtil {

    public static void main(String[] args) {
        GeneratorDTO generator = new GeneratorDTO();
        generator.setModule("product");
        generator.setDatabaseUrl(StrUtil.format(generator.getDatabaseUrl(), generator.getModule()));
        generator.setGenerateCustomer(true);
        generator.setTableNames(new String[]{"pms_sku_audit"});
        moduleGenerator(generator);
    }


    /**
     * 生成文件的主方法
     */
    public static void moduleGenerator(GeneratorDTO generator) {

        // 全局配置
        GlobalConfig globalConfig = getGlobalConfig(generator.getModule());
        // 数据源配置
        DataSourceConfig dataSourceConfig = getDataSourceConfig(generator.getDatabaseUrl(), generator.getDatabaseUserName(), generator.getDatabasePassword());
        // 包配置
        PackageConfig packageConfig = getPackageConfig(generator);
        // 策略配置
        StrategyConfig strategyConfig = getStrategyConfig(generator.getModule(), generator.getTableNames());
        // 模板配置
        TemplateConfig templateConfig = getTemplateConfig();
        DMallAutoGenerator dMallAutoGenerator = new DMallAutoGenerator();
        dMallAutoGenerator.setGenerator(generator);
        // 采用自定义代码生成器来完成
        DMallVelocityTemplateEngine templateEngine = new DMallVelocityTemplateEngine();
        templateEngine.setGenerator(generator);
        dMallAutoGenerator
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setPackageInfo(packageConfig)
                .setStrategy(strategyConfig)
                .setTemplate(templateConfig)
                // 配置自定义的生成引擎
                .setTemplateEngine(templateEngine)
                .execute();

    }

    /**
     * 全局配置，配置生成文件的目录
     */
    private static GlobalConfig getGlobalConfig(String module) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOpen(false)
                //生成文件的输出目录
                .setOutputDir(System.getProperty(MybatisPlusConstants.USER_DIR)
                        + StrUtil.format(MybatisPlusConstants.GENERATOR_MODULE_NAME, module, module)
                        + MybatisPlusConstants.SRC_MAIN_JAVA)
                //是否覆盖已有文件 默认覆盖
                .setFileOverride(true)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setActiveRecord(false)
                .setIdType(IdType.ID_WORKER)
                .setAuthor(MybatisPlusConstants.AUTHOR)
                .setDateType(DateType.ONLY_DATE)
                // 修改实体后缀 和 服务实现类前缀
                .setEntityName(MybatisPlusConstants.ENTITY_NAME)
                .setServiceImplName(MybatisPlusConstants.SERVICE_NAME);
        return globalConfig;
    }

    /**
     * 配置数据源
     */
    private static DataSourceConfig getDataSourceConfig(String databaseUrl, String databaseUserName, String databasePassword) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setDriverName(MybatisPlusConstants.DRIVER_NAME)
                .setUsername(databaseUserName)
                .setPassword(databasePassword)
                .setUrl(StrUtil.format(MybatisPlusConstants.DB_URL, databaseUrl));
        return dataSourceConfig;
    }

    /**
     * 配置生成包名
     */
    private static PackageConfig getPackageConfig(GeneratorDTO generator) {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setEntity(MybatisPlusConstants.ENTITY_PACKAGE_NAME)
                .setParent(MybatisPlusConstants.PACKAGE_PARENT_NAME)
                .setModuleName(generator.getModule() + StringPool.DOT + MybatisPlusConstants.PACKAGE_GENERATOR_NAME);
        return packageConfig;
    }

    /**
     * 定义策略
     */
    private static StrategyConfig getStrategyConfig(String module, String... tableNames) {
        StrategyConfig strategyConfig = new StrategyConfig();
        //使用lombok
        strategyConfig.setEntityLombokModel(true)
                // 实体名称驼峰命名
                .setNaming(NamingStrategy.underline_to_camel)
                // 实体属性驼峰命名
                .setColumnNaming(NamingStrategy.underline_to_camel)
                // 表前缀
                .setTablePrefix(module + StrUtil.UNDERLINE);
        if (tableNames != null) {
            strategyConfig.setInclude(tableNames);
        }

        // 自动填充值
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill(MybatisPlusConstants.GMT_CREATED, FieldFill.INSERT));
        tableFillList.add(new TableFill(MybatisPlusConstants.GMT_MODIFIED, FieldFill.INSERT_UPDATE));
        tableFillList.add(new TableFill(MybatisPlusConstants.IS_DELETED, FieldFill.INSERT));
        tableFillList.add(new TableFill(MybatisPlusConstants.CREATOR, FieldFill.INSERT));
        tableFillList.add(new TableFill(MybatisPlusConstants.MODIFIER, FieldFill.INSERT_UPDATE));
        strategyConfig.setTableFillList(tableFillList);
        strategyConfig.setLogicDeleteFieldName(MybatisPlusConstants.IS_DELETED);
        return strategyConfig;
    }

    /**
     * 自定义代码生成模板, 不生成controller
     * 在模版引擎的执行方法中会校验如果模版为空则不会执行writer()方法
     */
    private static TemplateConfig getTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity(MybatisPlusConstants.TEMPLATES_ENTITY) // entity模板采用自定义模板
                .setMapper(MybatisPlusConstants.TEMPLATES_MAPPER)// mapper模板采用自定义模板
                .setXml(null) // xml采用自定义模板
                .setService(MybatisPlusConstants.TEMPLATES_SERVICE) // service接口采用自定义模板
                .setServiceImpl(MybatisPlusConstants.TEMPLATES_SERVICE_IMPL) // serviceImpl模板采用自定义模板
                .setController(null); // 不生成controller
        return templateConfig;

    }

}