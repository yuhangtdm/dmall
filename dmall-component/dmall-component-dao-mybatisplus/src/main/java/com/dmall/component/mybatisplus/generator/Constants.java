package com.dmall.component.mybatisplus.generator;

/**
 * @description: 存储常量
 * @author: created by hang.yu on 2019/12/1 14:34
 */
public interface Constants {

    String DEFAULT_MODULE_NAME = "D:/dmall-service-impl/dmall-service-impl-product/dmall-service-impl-product-generator";

    String DEFAULT_BUSINESS_MODULE_NAME = "D:/dmall-service-impl/dmall-service-impl-product/dmall-service-impl-product-business";

    String DEFAULT_API_MODULE_NAME = "D:/dmall-service-api/dmall-service-api-product";


    String GENERATOR_MODULE_NAME = "/dmall-service-impl/dmall-service-impl-order/dmall-service-impl-order-generator";

    String API_MODULE_NAME = "/dmall-service-api/dmall-service-api-order";

    String BUSINESS_MODULE_NAME = "/dmall-service-impl/dmall-service-impl-order/dmall-service-impl-order-business";

    /**
     * db url
     */
    String DB_URL = "jdbc:mysql://localhost:3306/dmall_oms?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8";
    /**
     * username
     */
    String USERNAME = "root";
    /**
     * password
     */
    String PASSWORD = "root";
    /**
     * 驱动
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
     * 包的模块名
     */
    String PACKAGE_MODULE_NAME = "oms";

    /**
     * 包的固定名称
     */
    String PACKAGE_GENERATOR_NAME = "generator";

    String API = "api";

    String COMMON = "common";

    String REQUEST = "request";

    String RESPONSE = "response";

    /**
     * 实体的包名
     */
    String ENTITY_PACKAGE_NAME = "dataobject";

    String ENTITY_name = "%sDO";

    String SERVICE_NAME = "I%sServiceImpl";

    String GMT_CREATED = "gmt_created";

    String GMT_MODIFIED = "gmt_modified";

    String IS_DELETED = "is_deleted";

    String CREATOR = "creator";

    String MODIFIER = "modifier";

    String TEMPLATES_ENTITY = "templates/entity.java.vm";

    String TEMPLATES_MAPPER = "templates/mapper.java.vm";

    String TEMPLATES_MAPPER_XML = "templates/mapper.xml.vm";

    String TEMPLATES_SERVICE = "templates/service.java.vm";

    String TEMPLATES_SERVICE_IMPL = "templates/serviceImpl.java.vm";

    String TEMPLATES_DTO = "templates/dto.java.vm";

    String TEMPLATES_COMMON_REQUEST_DTO = "templates/commonRequestDto.java.vm";

    String TEMPLATES_COMMON_RESPONSE_DTO = "templates/commonResponseDto.java.vm";

    String TEMPLATES_LIST_REQEUST_DTO = "templates/listReqeustDto.java.vm";

    String TEMPLATES_PAGE_REQEUST_DTO = "templates/pageReqeustDto.java.vm";

    String TEMPLATES_SAVE_REQEUST_DTO = "templates/saveReqeustDto.java.vm";

    String TEMPLATES_UPDATE_REQUEST_DTO = "templates/updateRequestDto.java.vm";

    String TEMPLATES_BUSINESS_SERVICE = "templates/businessService.java.vm";

    String TEMPLATES_BUSINESS_SERVICE_IMPL = "templates/businessServiceImpl.java.vm";

    String TEMPLATES_SAVEHANDLER = "templates/saveHandler.java.vm";

    String TEMPLATES_DELETEHANDLER = "templates/deleteHandler.java.vm";

    String TEMPLATES_UPDATEHANDLER = "templates/updateHandler.java.vm";

    String TEMPLATES_GETHANDLER = "templates/getHandler.java.vm";

    String TEMPLATES_LISTHANDLER = "templates/listHandler.java.vm";

    String TEMPLATES_PAGEHANDLER = "templates/pageHandler.java.vm";

    String TEMPLATES_ERRORENUM = "templates/errorEnum.java.vm";

    String SRC_MAIN_JAVA = "/src/main/java";

    String SRC_MAIN_RESOURCES = "/src/main/resources";

    String COM_DMALL = "/com/dmall/";

    String DTO = "dto";

    String SERVICE = "service";

    String IMPL = "impl";

    String HANDLER = "handler";

    String ENUMS = "enums";
}
