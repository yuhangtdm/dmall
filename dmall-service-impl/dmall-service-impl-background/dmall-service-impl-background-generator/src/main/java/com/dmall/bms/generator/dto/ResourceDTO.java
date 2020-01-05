package com.dmall.bms.generator.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 资源表
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ResourceDTO", description = "资源表")
public class ResourceDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "id", position = 1)
    private Long id;

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

    @ApiModelProperty(value = "创建人", position = 9)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 10)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 11)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 12)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 13)
    private String isDeleted;

}
