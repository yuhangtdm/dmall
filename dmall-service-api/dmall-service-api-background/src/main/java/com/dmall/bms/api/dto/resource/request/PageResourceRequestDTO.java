package com.dmall.bms.api.dto.resource.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.common.dto.PageRequestDTO;

/**
 * @description: 资源分页请求实体
 * @author: created by hang.yu on 2020-01-05 18:36:37
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageResourceRequestDTO", description = "资源分页请求实体")
public class PageResourceRequestDTO extends PageRequestDTO {


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
