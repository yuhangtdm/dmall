package com.dmall.component.mybatisplus.generator;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.File;
import java.util.List;

/**
 * @description: 自定义生成器
 * @author: created by hang.yu on 2019/12/1 14:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DMallAutoGenerator extends AutoGenerator {

    /**
     * 是否生成自定义的文件
     */
    private GeneratorDTO generator;

    @Override
    protected List<TableInfo> getAllTableInfoList(ConfigBuilder config) {
        List<TableInfo> allTableInfoList = super.getAllTableInfoList(config);
        // 自定义配置模版， 如果你想添加一个新的类，可以在资源文件目录中的templates文件夹下
        // 添加自定义的模版文件
        config.setInjectionConfig(getInjectionConfig(config));
        return allTableInfoList;
    }

    public InjectionConfig getInjectionConfig(ConfigBuilder config) {
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {}
        };

        injectionConfig.setFileCreate(new DMallFileCreate() {

            /**
             * 自定义文件的配置
             */
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, DMallFileType fileType, String filePath) {
                // 如果不创建则返回false
                if (!generator.isGenerateCustomer()) {
                    return false;
                }
                // 判断自定义文件夹是否需要创建,这里调用默认的方法
                checkDir(filePath);
                // 对于已存在的文件，只需覆盖 XML
                File file = new File(filePath);
                boolean exist = file.exists();
                if (exist) {
                    return DMallFileType.XML == fileType;
                }
                // 不存在的文件都需要创建
                return true;
            }

            /**
             * mybatisPlus本身的配置
             */
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 自定义的文件不生成
                if (FileType.OTHER == fileType) {
                    return false;
                }
                // 判断自定义文件夹是否需要创建,这里调用默认的方法
                checkDir(filePath);
                // 对于已存在的文件，只需重复生成 entity
                File file = new File(filePath);
                boolean exist = file.exists();
                if (exist) {
                    // 实体文件需要腹泻
                    return FileType.ENTITY == fileType;
                }
                // 不存在的文件都需要创建
                return true;
            }
        });

        List<FileOutConfig> list = Lists.newArrayList();

        // xml
        FileOutConfig xmlOutConfig = new FileOutConfig(MybatisPlusConstants.TEMPLATES_MAPPER_XML) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entityName = StrUtil.removeSuffix(tableInfo.getEntityName(), FileEnum.DO.getCode());
                return System.getProperty(MybatisPlusConstants.USER_DIR)
                    + StrUtil.format(MybatisPlusConstants.GENERATOR_MODULE_NAME, getGenerator().getModule(),
                        getGenerator().getModule())
                    + MybatisPlusConstants.SRC_MAIN_RESOURCES
                    + StrUtil.format(MybatisPlusConstants.MAPPER_NAME_TEMPLATE, entityName);
            }
        };
        list.add(xmlOutConfig);

        // save request dto
        FileOutConfig saveOutConfig = new FileOutConfig(MybatisPlusConstants.TEMPLATES_SAVE_REQUEST_DTO) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entityName = StrUtil.removeSuffix(tableInfo.getEntityName(), FileEnum.DO.getCode());
                return getDtoPath(entityName, MybatisPlusConstants.REQUEST, HandlerEnum.SAVE.getCode(),
                    FileEnum.REQUEST_DTO.getCode());
            }
        };
        list.add(saveOutConfig);

        // update request dto
        FileOutConfig updateOutConfig = new FileOutConfig(MybatisPlusConstants.TEMPLATES_UPDATE_REQUEST_DTO) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entityName = StrUtil.removeSuffix(tableInfo.getEntityName(), FileEnum.DO.getCode());
                return getDtoPath(entityName, MybatisPlusConstants.REQUEST, HandlerEnum.UPDATE.getCode(),
                    FileEnum.REQUEST_DTO.getCode());
            }
        };
        list.add(updateOutConfig);

        // list request dto
        FileOutConfig listOutConfig = new FileOutConfig(MybatisPlusConstants.TEMPLATES_LIST_REQUEST_DTO) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entityName = StrUtil.removeSuffix(tableInfo.getEntityName(), FileEnum.DO.getCode());
                return getDtoPath(entityName, MybatisPlusConstants.REQUEST, HandlerEnum.LIST.getCode(),
                    FileEnum.REQUEST_DTO.getCode());
            }
        };
        list.add(listOutConfig);

        // page request dto
        FileOutConfig pageOutConfig = new FileOutConfig(MybatisPlusConstants.TEMPLATES_PAGE_REQUEST_DTO) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entityName = StrUtil.removeSuffix(tableInfo.getEntityName(), FileEnum.DO.getCode());
                return getDtoPath(entityName, MybatisPlusConstants.REQUEST, HandlerEnum.PAGE.getCode(),
                    FileEnum.REQUEST_DTO.getCode());
            }
        };
        list.add(pageOutConfig);

        // response dto
        FileOutConfig responseOutConfig = new FileOutConfig(MybatisPlusConstants.TEMPLATES_RESPONSE_DTO) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entityName = StrUtil.removeSuffix(tableInfo.getEntityName(), FileEnum.DO.getCode());
                return getDtoPath(entityName, MybatisPlusConstants.RESPONSE, StrUtil.EMPTY,
                    FileEnum.RESPONSE_DTO.getCode());
            }
        };
        list.add(responseOutConfig);

        // service api
        FileOutConfig serviceOutConfig = new FileOutConfig(MybatisPlusConstants.TEMPLATES_BUSINESS_SERVICE_API) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entityName = StrUtil.removeSuffix(tableInfo.getEntityName(), FileEnum.DO.getCode());
                return getServicePath(entityName);
            }
        };
        list.add(serviceOutConfig);

        // serviceImpl
        FileOutConfig serviceImplOutConfig = new FileOutConfig(MybatisPlusConstants.TEMPLATES_BUSINESS_SERVICE_IMPL) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entityName = StrUtil.removeSuffix(tableInfo.getEntityName(), FileEnum.DO.getCode());
                return getServiceImplPath(entityName);
            }
        };
        list.add(serviceImplOutConfig);

        // save Handler
        FileOutConfig saveHandlerOutConfig = new FileOutConfig(MybatisPlusConstants.TEMPLATES_SAVE_HANDLER) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entityName = StrUtil.removeSuffix(tableInfo.getEntityName(), FileEnum.DO.getCode());
                return getHandlerPath(entityName, HandlerEnum.SAVE.getCode());
            }
        };
        list.add(saveHandlerOutConfig);

        // delete Handler
        FileOutConfig deleteHandlerOutConfig = new FileOutConfig(MybatisPlusConstants.TEMPLATES_DELETE_HANDLER) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entityName = StrUtil.removeSuffix(tableInfo.getEntityName(), FileEnum.DO.getCode());
                return getHandlerPath(entityName, HandlerEnum.DELETE.getCode());
            }
        };
        list.add(deleteHandlerOutConfig);

        // update Handler
        FileOutConfig updateHandlerOutConfig = new FileOutConfig(MybatisPlusConstants.TEMPLATES_UPDATE_HANDLER) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entityName = StrUtil.removeSuffix(tableInfo.getEntityName(), FileEnum.DO.getCode());
                return getHandlerPath(entityName, HandlerEnum.UPDATE.getCode());
            }
        };
        list.add(updateHandlerOutConfig);

        // get Handler
        FileOutConfig getHandlerOutConfig = new FileOutConfig(MybatisPlusConstants.TEMPLATES_GET_HANDLER) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entityName = StrUtil.removeSuffix(tableInfo.getEntityName(), FileEnum.DO.getCode());
                return getHandlerPath(entityName, HandlerEnum.GET.getCode());
            }
        };
        list.add(getHandlerOutConfig);

        // list Handler
        FileOutConfig listHandlerOutConfig = new FileOutConfig(MybatisPlusConstants.TEMPLATES_LIST_HANDLER) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entityName = StrUtil.removeSuffix(tableInfo.getEntityName(), FileEnum.DO.getCode());
                return getHandlerPath(entityName, HandlerEnum.LIST.getCode());
            }
        };
        list.add(listHandlerOutConfig);

        // page Handler
        FileOutConfig pageHandlerOutConfig = new FileOutConfig(MybatisPlusConstants.TEMPLATES_PAGE_HANDLER) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entityName = StrUtil.removeSuffix(tableInfo.getEntityName(), FileEnum.DO.getCode());
                return getHandlerPath(entityName, HandlerEnum.PAGE.getCode());
            }
        };
        list.add(pageHandlerOutConfig);

        injectionConfig.setFileOutConfigList(list);
        return injectionConfig;
    }

    /**
     * dto的路径
     */
    private String getDtoPath(String entityName, String operation, String prefix, String suffix) {
        StringBuilder append = api()
            .append(MybatisPlusConstants.DTO)
            .append(File.separator)
            .append(entityName.toLowerCase())
            .append(File.separator)
            .append(operation)
            .append(File.separator)
            .append(prefix)
            .append(entityName)
            .append(suffix)
            .append(StringPool.DOT_JAVA);
        return append.toString();
    }

    /**
     * service的路径
     */
    private String getServicePath(String entityName) {
        StringBuilder append = api()
            .append(MybatisPlusConstants.SERVICE)
            .append(File.separator)
            .append(entityName)
            .append(FileEnum.SERVICE_API.getCode())
            .append(StringPool.DOT_JAVA);
        return append.toString();
    }

    /**
     * serviceImpl的路径
     */
    private String getServiceImplPath(String entityName) {
        StringBuilder append = impl(entityName)
            .append(entityName)
            .append(FileEnum.SERVICE_IMPL.getCode())
            .append(StringPool.DOT_JAVA);
        return append.toString();
    }

    /**
     * handler的路径
     */
    private String getHandlerPath(String entityName, String operator) {
        return impl(entityName)
            .append(MybatisPlusConstants.HANDLER)
            .append(File.separator)
            .append(operator)
            .append(entityName)
            .append(FileEnum.HANDLER.getCode())
            .append(StringPool.DOT_JAVA).toString();
    }

    /**
     * api的路径
     */
    private StringBuilder api() {
        return new StringBuilder()
            .append(System.getProperty(MybatisPlusConstants.USER_DIR))
            .append(StrUtil.format(MybatisPlusConstants.API_MODULE_NAME, generator.getModule()))
            .append(MybatisPlusConstants.SRC_MAIN_JAVA)
            .append(MybatisPlusConstants.COM_DMALL)
            .append(generator.getBusiness())
            .append(File.separator)
            .append(MybatisPlusConstants.API)
            .append(File.separator);
    }

    /**
     * impl的路径
     */
    private StringBuilder impl(String entityName) {
        return new StringBuilder()
            .append(System.getProperty(MybatisPlusConstants.USER_DIR))
            .append(
                StrUtil.format(MybatisPlusConstants.BUSINESS_MODULE_NAME, generator.getModule(), generator.getModule()))
            .append(MybatisPlusConstants.SRC_MAIN_JAVA)
            .append(MybatisPlusConstants.COM_DMALL)
            .append(generator.getBusiness())
            .append(File.separator)
            .append(MybatisPlusConstants.SERVICE)
            .append(File.separator)
            .append(MybatisPlusConstants.IMPL)
            .append(File.separator)
            .append(entityName.toLowerCase())
            .append(File.separator);
    }

}