package com.dmall.component.mybatisplus.generator;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.toolkit.PackageHelper;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @description: 用于构建模板文件中的变量
 * @author: created by hang.yu on 2019/12/1 14:16
 */
@Slf4j
public class DMallVelocityTemplateEngine extends VelocityTemplateEngine {

    @Override
    public Map<String, Object> getObjectMap(TableInfo tableInfo) {
        Map<String, Object> objectMap = super.getObjectMap(tableInfo);
        String entityName = StrUtil.removeSuffix(tableInfo.getEntityName(), "DO");
        String comment = StrUtil.removeSuffix(tableInfo.getComment(), "表");

        // 设置包名
        setPackageName(objectMap, entityName);
        // 设置类名
        setClassName(objectMap, entityName, comment, tableInfo.getMapperName());
        // 设置注释
        setComment(objectMap, comment);
        // 设置操作
        setOperation(objectMap, comment);

        objectMap.put("datetime", DateUtil.now());
        objectMap.put("requestMapping", "/" + StrUtil.lowerFirst(entityName));
        return objectMap;
    }

    /**
     * 重写输出方法达到自定义的功能
     */
    @Override
    public AbstractTemplateEngine batchOutput() {
        // 先生成自定义文件
        try {
            List<TableInfo> tableInfoList = getConfigBuilder().getTableInfoList();
            for (TableInfo tableInfo : tableInfoList) {
                Map<String, Object> objectMap = getObjectMap(tableInfo);
                // 自定义内容
                InjectionConfig injectionConfig = getConfigBuilder().getInjectionConfig();
                if (null != injectionConfig) {
                    injectionConfig.initMap();
                    List<FileOutConfig> focList = injectionConfig.getFileOutConfigList();
                    if (CollectionUtils.isNotEmpty(focList)) {
                        for (FileOutConfig foc : focList) {
                            switch (foc.getTemplatePath()) {
                                case Constants.TEMPLATES_BUSINESS_SERVICE: {
                                    if (isCreate(DMallFileType.SERVICE_API, foc.outputFile(tableInfo))) {
                                        writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                                    }
                                    break;
                                }
                                case Constants.TEMPLATES_BUSINESS_SERVICE_IMPL: {
                                    if (isCreate(DMallFileType.SERVICE_IMPL, foc.outputFile(tableInfo))) {
                                        writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                                    }
                                    break;
                                }
                                case Constants.TEMPLATES_DTO: {
                                    if (isCreate(DMallFileType.DTO, foc.outputFile(tableInfo))) {
                                        writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                                    }
                                    break;
                                }
                                case Constants.TEMPLATES_MAPPER_XML: {
                                    if (isCreate(DMallFileType.XML, foc.outputFile(tableInfo))) {
                                        writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                                    }
                                    break;
                                }
                                case Constants.TEMPLATES_COMMON_REQUEST_DTO: {
                                    if (isCreate(DMallFileType.BUSINESS_DTO, foc.outputFile(tableInfo))) {
                                        writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                                    }
                                    break;
                                }
                                case Constants.TEMPLATES_COMMON_RESPONSE_DTO: {
                                    if (isCreate(DMallFileType.BUSINESS_DTO, foc.outputFile(tableInfo))) {
                                        writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                                    }
                                    break;
                                }
                                case Constants.TEMPLATES_LIST_REQEUST_DTO: {
                                    if (isCreate(DMallFileType.BUSINESS_DTO, foc.outputFile(tableInfo))) {
                                        writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                                    }
                                    break;
                                }
                                case Constants.TEMPLATES_PAGE_REQEUST_DTO: {
                                    if (isCreate(DMallFileType.BUSINESS_DTO, foc.outputFile(tableInfo))) {
                                        writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                                    }
                                    break;
                                }
                                case Constants.TEMPLATES_SAVE_REQEUST_DTO: {
                                    if (isCreate(DMallFileType.BUSINESS_DTO, foc.outputFile(tableInfo))) {
                                        writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                                    }
                                    break;
                                }
                                case Constants.TEMPLATES_UPDATE_REQUEST_DTO: {
                                    if (isCreate(DMallFileType.BUSINESS_DTO, foc.outputFile(tableInfo))) {
                                        writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                                    }
                                    break;
                                }
                                case Constants.TEMPLATES_SAVEHANDLER: {
                                    if (isCreate(DMallFileType.HANDLER, foc.outputFile(tableInfo))) {
                                        writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                                    }
                                    break;
                                }
                                case Constants.TEMPLATES_DELETEHANDLER: {
                                    if (isCreate(DMallFileType.HANDLER, foc.outputFile(tableInfo))) {
                                        writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                                    }
                                    break;
                                }
                                case Constants.TEMPLATES_UPDATEHANDLER: {
                                    if (isCreate(DMallFileType.HANDLER, foc.outputFile(tableInfo))) {
                                        writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                                    }
                                    break;
                                }
                                case Constants.TEMPLATES_GETHANDLER: {
                                    if (isCreate(DMallFileType.HANDLER, foc.outputFile(tableInfo))) {
                                        writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                                    }
                                    break;
                                }
                                case Constants.TEMPLATES_LISTHANDLER: {
                                    if (isCreate(DMallFileType.HANDLER, foc.outputFile(tableInfo))) {
                                        writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                                    }
                                    break;
                                }
                                case Constants.TEMPLATES_PAGEHANDLER: {
                                    if (isCreate(DMallFileType.HANDLER, foc.outputFile(tableInfo))) {
                                        writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                                    }
                                    break;
                                }
                                case Constants.TEMPLATES_ERRORENUM: {
                                    if (isCreate(DMallFileType.ERROR_ENUM, foc.outputFile(tableInfo))) {
                                        writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                                    }
                                    break;
                                }
                            }

                        }
                    }
                }
            }
            log.info("==========================生成自定义文件成功==========================");
        } catch (Exception e) {
            log.error("无法创建文件，请检查配置信息！", e);
        }
        return super.batchOutput();
    }

    protected boolean isCreate(DMallFileType fileType, String filePath) {
        ConfigBuilder cb = getConfigBuilder();
        // 自定义判断
        InjectionConfig ic = cb.getInjectionConfig();
        if (null != ic && null != ic.getFileCreate()) {
            if (ic.getFileCreate() instanceof DMallFileCreate) {
                DMallFileCreate dc = (DMallFileCreate) ic.getFileCreate();
                return dc.isCreate(cb, fileType, filePath);
            }
        }
        // 全局判断【默认】
        File file = new File(filePath);
        boolean exist = file.exists();
        if (!exist) {
            PackageHelper.mkDir(file.getParentFile());
        }
        return !exist || getConfigBuilder().getGlobalConfig().isFileOverride();
    }

    /**
     * 设置包名
     */
    private void setPackageName(Map<String, Object> objectMap, String entityName) {
        StringBuilder dtoPackage = new StringBuilder(Constants.PACKAGE_PARENT_NAME)
                .append(StringPool.DOT)
                .append(Constants.PACKAGE_MODULE_NAME)
                .append(StringPool.DOT)
                .append(Constants.PACKAGE_GENERATOR_NAME)
                .append(StringPool.DOT)
                .append(Constants.DTO);
        objectMap.put("dtoPackage", dtoPackage.toString());
        // common dto Package
        objectMap.put("commonDtoPackage", getDtoPackage(entityName, Constants.COMMON));
        // request dto package
        objectMap.put("requestDtoPackage", getDtoPackage(entityName, Constants.REQUEST));
        // response dto package
        objectMap.put("responseDtoPackage", getDtoPackage(entityName, Constants.RESPONSE));

        objectMap.put("servicePackage", getServicePackage());
        objectMap.put("serviceImplPackage", getServiceImplPackage(entityName));
        objectMap.put("handlerPackage", getHandlerPackage(entityName));
        objectMap.put("errorEnumPackage", getEnumsPackage(entityName));

    }

    /**
     * 设置类名
     */
    private void setClassName(Map<String, Object> objectMap, String entityName, String comment, String mapperName) {
        objectMap.put("dto", StrUtil.format("{}DTO", entityName));
        objectMap.put("commonRequestDto", StrUtil.format("Common{}RequestDTO", entityName));
        objectMap.put("commonResponseDto", StrUtil.format("Common{}ResponseDTO", entityName));
        objectMap.put("listRequestDto", StrUtil.format("List{}RequestDTO", entityName));
        objectMap.put("pageRequestDto", StrUtil.format("Page{}RequestDTO", entityName));
        objectMap.put("saveRequestDto", StrUtil.format("Save{}RequestDTO", entityName));
        objectMap.put("updateRequestDto", StrUtil.format("Update{}RequestDTO", entityName));
        objectMap.put("businessService", StrUtil.format("{}Service", entityName));
        objectMap.put("businessServiceImpl", StrUtil.format("{}ServiceImpl", entityName));
        objectMap.put("saveHandler", StrUtil.format("Save{}Handler", entityName));
        objectMap.put("deleteHandler", StrUtil.format("Delete{}Handler", entityName));
        objectMap.put("updateHandler", StrUtil.format("Update{}Handler", entityName));
        objectMap.put("getHandler", StrUtil.format("Get{}Handler", entityName));
        objectMap.put("listHandler", StrUtil.format("List{}Handler", entityName));
        objectMap.put("pageHandler", StrUtil.format("Page{}Handler", entityName));
        objectMap.put("errorEnum", StrUtil.format("{}ErrorEnum", entityName));
        objectMap.put("saveHandlerName", StrUtil.format("save{}Handler", entityName));
        objectMap.put("deleteHandlerName", StrUtil.format("delete{}Handler", entityName));
        objectMap.put("updateHandlerName", StrUtil.format("update{}Handler", entityName));
        objectMap.put("getHandlerName", StrUtil.format("get{}Handler", entityName));
        objectMap.put("listHandlerName", StrUtil.format("list{}Handler", entityName));
        objectMap.put("pageHandlerName", StrUtil.format("page{}Handler", entityName));
        objectMap.put("enumName", entityName.toUpperCase());
        objectMap.put("object", StrUtil.lowerFirst(entityName));
        objectMap.put("mapperName", StrUtil.lowerFirst(mapperName));
        objectMap.put("comment", comment);
    }

    /**
     * 设置注释
     */
    private void setComment(Map<String, Object> objectMap, String comment) {
        objectMap.put("commonRequestComment", StrUtil.format("{}公共请求实体", comment));
        objectMap.put("commonResponseComment", StrUtil.format("{}公共响应实体", comment));
        objectMap.put("listRequestComment", StrUtil.format("{}列表请求实体", comment));
        objectMap.put("pageRequestComment", StrUtil.format("{}分页请求实体", comment));
        objectMap.put("saveRequestComment", StrUtil.format("新增{}请求实体", comment));
        objectMap.put("updateRequestComment", StrUtil.format("修改{}请求实体", comment));
        objectMap.put("serviceComment", StrUtil.format("{}服务", comment));
        objectMap.put("serviceImplComment", StrUtil.format("{}服务实现", comment));
        objectMap.put("errorEnumComment", StrUtil.format("{}错误枚举", comment));
        objectMap.put("saveHandlerComment", StrUtil.format("新增{}处理器", comment));
        objectMap.put("updateHandlerComment", StrUtil.format("修改{}处理器", comment));
        objectMap.put("deleteHandlerComment", StrUtil.format("删除{}处理器", comment));
        objectMap.put("getHandlerComment", StrUtil.format("查询{}处理器", comment));
        objectMap.put("listHandlerComment", StrUtil.format("{}列表处理器", comment));
        objectMap.put("pageHandlerComment", StrUtil.format("{}分页处理器", comment));
    }

    /**
     * 设置操作
     */
    private void setOperation(Map<String, Object> objectMap, String comment) {
        objectMap.put("saveOperation", StrUtil.format("新增{}", comment));
        objectMap.put("updateOperation", StrUtil.format("修改{}", comment));
        objectMap.put("pageOperation", StrUtil.format("{}分页", comment));
        objectMap.put("listOperation", StrUtil.format("{}列表", comment));
        objectMap.put("deleteOperation", StrUtil.format("删除{}", comment));
        objectMap.put("getOperation", StrUtil.format("根据id查询{}", comment));
        objectMap.put("id", StrUtil.format("{}id", comment));
    }


    private String getDtoPackage(String func, String mod) {
        StringBuilder sb = new StringBuilder(Constants.PACKAGE_PARENT_NAME)
                .append(StringPool.DOT)
                .append(Constants.PACKAGE_MODULE_NAME)
                .append(StringPool.DOT)
                .append(Constants.API)
                .append(StringPool.DOT)
                .append(Constants.DTO)
                .append(StringPool.DOT)
                .append(func.toLowerCase())
                .append(StringPool.DOT)
                .append(mod);
        return sb.toString();
    }

    private String getServicePackage() {
        StringBuilder sb = new StringBuilder(Constants.PACKAGE_PARENT_NAME)
                .append(StringPool.DOT)
                .append(Constants.PACKAGE_MODULE_NAME)
                .append(StringPool.DOT)
                .append(Constants.API)
                .append(StringPool.DOT)
                .append(Constants.SERVICE);
        return sb.toString();
    }

    private String getServiceImplPackage(String entityName) {
        StringBuilder sb = new StringBuilder(Constants.PACKAGE_PARENT_NAME)
                .append(StringPool.DOT)
                .append(Constants.PACKAGE_MODULE_NAME)
                .append(StringPool.DOT)
                .append(Constants.SERVICE)
                .append(StringPool.DOT)
                .append(Constants.IMPL)
                .append(StringPool.DOT)
                .append(entityName.toLowerCase());
        return sb.toString();
    }

    private String getHandlerPackage(String entityName) {
        StringBuilder sb = new StringBuilder(getServiceImplPackage(entityName))
                .append(StringPool.DOT)
                .append(Constants.HANDLER);
        return sb.toString();
    }

    private String getEnumsPackage(String entityName) {
        StringBuilder sb = new StringBuilder(getServiceImplPackage(entityName))
                .append(StringPool.DOT)
                .append(Constants.ENUMS);
        return sb.toString();
    }
}
