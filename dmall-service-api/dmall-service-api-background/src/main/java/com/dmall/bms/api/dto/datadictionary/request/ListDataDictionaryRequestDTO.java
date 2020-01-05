package com.dmall.bms.api.dto.datadictionary.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 数据字典列表请求实体
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListDataDictionaryRequestDTO", description = "数据字典列表请求实体")
public class ListDataDictionaryRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;


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


}
