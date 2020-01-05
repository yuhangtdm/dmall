package com.dmall.bms.api.dto.resource.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 资源列表请求实体
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListResourceRequestDTO", description = "资源列表请求实体")
public class ListResourceRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "父级id", position = 2)
    private Long parentId;

    @ApiModelProperty(value = "资源码", position = 3)
    private String code;

    @ApiModelProperty(value = "资源名称", position = 4)
    private String name;

    @ApiModelProperty(value = "图标", position = 5)
    private String icon;

    @ApiModelProperty(value = "资源类型 1-目录;2-菜单;3-接口地址", position = 6)
    private Integer type;

    @ApiModelProperty(value = "资源地址", position = 7)
    private String uri;

    @ApiModelProperty(value = "资源请求方式 1-GET;2-POST;3-PUT;4-DELETE", position = 8)
    private Integer method;


}
