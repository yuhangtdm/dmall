package com.dmall.component.mybatisplus.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: dmall的mybatisPlus生成器
 * @author: created by hang.yu on 2019/11/2 16:50
 */
public class MybatisPlusGenerator {


    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/dmall-service-impl/dmall-service-impl-product/dmall-service-impl-product-generator/src/main/java");
        gc.setAuthor("hang.yu");
        gc.setOpen(false);
        gc.setBaseResultMap(true);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setEntityName("%sDO");
        gc.setServiceName("%sService");
        gc.setIdType(IdType.AUTO);
        gc.setFileOverride(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://106.15.188.249:3306/dmall_pms?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("f088df87e10c40fe804c54e88d54b8e4");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.dmall");
        pc.setModuleName("pms.generator");
        pc.setEntity("dataobject");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
//        strategy.setInclude("");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntityBuilderModel(true);
        strategy.setTablePrefix( "pms_");
        List<TableFill> tableFillList =  new ArrayList<>();
        tableFillList.add(new TableFill("gmt_created", FieldFill.INSERT));
        tableFillList.add(new TableFill("gmt_modified", FieldFill.INSERT_UPDATE));
        tableFillList.add(new TableFill("is_deleted", FieldFill.INSERT));
        strategy.setTableFillList(tableFillList);
        strategy.setLogicDeleteFieldName("is_deleted");
        mpg.setStrategy(strategy);
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        mpg.setCfg(cfg);
        mpg.execute();
    }

}
