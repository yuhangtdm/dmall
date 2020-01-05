package com.dmall.bms.generator.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 数据字典表
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "DataDictionaryDTO", description = "数据字典表")
public class DataDictionaryDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "父id", position = 2)
    private Long parentId;

    @ApiModelProperty(value = "字典类型", position = 3)
    private String dictType;

    @ApiModelProperty(value = "字典名称", position = 4)
    private String dictName;

    @ApiModelProperty(value = "字典代码", position = 5)
    private String dictCode;

    @ApiModelProperty(value = "字典值", position = 6)
    private String dictValue;

    @ApiModelProperty(value = "排序", position = 7)
    private String sort;

    @ApiModelProperty(value = "创建人", position = 8)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 9)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 10)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 11)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 12)
    private String isDeleted;

}
