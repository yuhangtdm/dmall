package com.dmall.component.mybatisplus.generator;

/**
 * @description: MybatisPlus常量
 * @author: created by hang.yu on 2019/12/1 14:34
 */
public interface MybatisPlusConstants {

    /**
     * GENERATOR_MODULE_NAME
     */
    String GENERATOR_MODULE_NAME = "/dmall-service-impl/dmall-service-impl-{}/dmall-service-impl-{}-generator";

    /**
     * API_MODULE_NAME
     */
    String API_MODULE_NAME = "/dmall-service-api/dmall-service-api-{}";

    /**
     * BUSINESS_MODULE_NAME
     */
    String BUSINESS_MODULE_NAME = "/dmall-service-impl/dmall-service-impl-{}/dmall-service-impl-{}-business";

    /**
     * db url
     */
    String DB_URL = "jdbc:mysql://{}?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8";

    /**
     * 驱动名称
     */
    String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    /**
     * 作者
     */
    String AUTHOR = "hang.yu";

    /**
     * 包的父名称
     */
    String PACKAGE_PARENT_NAME = "com.dmall";

    /**
     * generator的包名
     */
    String PACKAGE_GENERATOR_NAME = "generator";

    /**
     * api包名
     */
    String API = "api";

    /**
     * request包名
     */
    String REQUEST = "request";

    /**
     * response包名
     */
    String RESPONSE = "response";

    /**
     * dataobject包名
     */
    String ENTITY_PACKAGE_NAME = "dataobject";

    /**
     * 实体名称规则
     */
    String ENTITY_NAME = "%sDO";

    /**
     * 服务名称规则
     */
    String SERVICE_NAME = "I%sServiceImpl";

    /**
     * GMT_CREATED
     */
    String GMT_CREATED = "gmtCreated";

    /**
     * GMT_MODIFIED
     */
    String GMT_MODIFIED = "gmtModified";

    /**
     * IS_DELETED
     */
    String IS_DELETED = "isDeleted";

    /**
     * CREATOR
     */
    String CREATOR = "creator";

    /**
     * MODIFIER
     */
    String MODIFIER = "modifier";

    /**
     * TEMPLATES_ENTITY
     */
    String TEMPLATES_ENTITY = "templates/mp/entity.java.vm";

    /**
     * TEMPLATES_MAPPER
     */
    String TEMPLATES_MAPPER = "templates/mp/mapper.java.vm";

    /**
     * TEMPLATES_MAPPER_XML
     */
    String TEMPLATES_MAPPER_XML = "templates/mp/mapper.xml.vm";

    /**
     * TEMPLATES_SERVICE
     */
    String TEMPLATES_SERVICE = "templates/mp/service.java.vm";

    /**
     * TEMPLATES_SERVICE_IMPL
     */
    String TEMPLATES_SERVICE_IMPL = "templates/mp/serviceImpl.java.vm";

    /**
     * TEMPLATES_LIST_REQUEST_DTO
     */
    String TEMPLATES_LIST_REQUEST_DTO = "templates/customer/dto/listRequestDto.java.vm";

    /**
     * TEMPLATES_PAGE_REQUEST_DTO
     */
    String TEMPLATES_PAGE_REQUEST_DTO = "templates/customer/dto/pageRequestDto.java.vm";

    /**
     * TEMPLATES_SAVE_REQUEST_DTO
     */
    String TEMPLATES_SAVE_REQUEST_DTO = "templates/customer/dto/saveRequestDto.java.vm";

    /**
     * TEMPLATES_UPDATE_REQUEST_DTO
     */
    String TEMPLATES_UPDATE_REQUEST_DTO = "templates/customer/dto/updateRequestDto.java.vm";

    /**
     * TEMPLATES_UPDATE_REQUEST_DTO
     */
    String TEMPLATES_RESPONSE_DTO = "templates/customer/dto/responseDto.java.vm";

    /**
     * TEMPLATES_BUSINESS_SERVICE
     */
    String TEMPLATES_BUSINESS_SERVICE_API = "templates/customer/businessService.java.vm";

    /**
     * TEMPLATES_BUSINESS_SERVICE_IMPL
     */
    String TEMPLATES_BUSINESS_SERVICE_IMPL = "templates/customer/businessServiceImpl.java.vm";

    /**
     * TEMPLATES_SAVE_HANDLER
     */
    String TEMPLATES_SAVE_HANDLER = "templates/customer/handler/saveHandler.java.vm";

    /**
     * TEMPLATES_DELETE_HANDLER
     */
    String TEMPLATES_DELETE_HANDLER = "templates/customer/handler/deleteHandler.java.vm";

    /**
     * TEMPLATES_UPDATE_HANDLER
     */
    String TEMPLATES_UPDATE_HANDLER = "templates/customer/handler/updateHandler.java.vm";

    /**
     * TEMPLATES_GET_HANDLER
     */
    String TEMPLATES_GET_HANDLER = "templates/customer/handler/getHandler.java.vm";

    /**
     * TEMPLATES_LIST_HANDLER
     */
    String TEMPLATES_LIST_HANDLER = "templates/customer/handler/listHandler.java.vm";

    /**
     * TEMPLATES_PAGE_HANDLER
     */
    String TEMPLATES_PAGE_HANDLER = "templates/customer/handler/pageHandler.java.vm";

    /**
     * SRC_MAIN_JAVA
     */
    String SRC_MAIN_JAVA = "/src/main/java";

    /**
     * SRC_MAIN_RESOURCES
     */
    String SRC_MAIN_RESOURCES = "/src/main/resources";

    /**
     * COM_DMALL
     */
    String COM_DMALL = "/com/dmall/";

    /**
     * DTO
     */
    String DTO = "dto";

    /**
     * SERVICE
     */
    String SERVICE = "service";

    /**
     * impl
     */
    String IMPL = "impl";

    /**
     * handler
     */
    String HANDLER = "handler";

    /**
     * 表
     */
    String TABLE_REMARK = "表";

    /**
     * mapper名称模板
     */
    String MAPPER_NAME_TEMPLATE = "/mapper/{}Mapper.xml";

    /**
     * user.dir
     */
    String USER_DIR = "user.dir";

}
