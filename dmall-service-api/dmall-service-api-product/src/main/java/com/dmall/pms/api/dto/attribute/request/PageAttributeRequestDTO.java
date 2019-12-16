package com.dmall.pms.api.dto.attribute.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.component.web.entity.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: 属性分页请求实体
 * @author: created by hang.yu on 2019-12-16 15:14:49
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageAttributeRequestDTO", description =  "属性分页请求实体")
public class PageAttributeRequestDTO  extends PageRequestDTO {




    @ApiModelProperty(value = "属性分类id", position = 2)
    private Long attributeTypeId;

    @ApiModelProperty(value = "商品分类id", position = 3)
    private Long categoryId;

    @ApiModelProperty(value = "商品分类path", position = 4)
    private String cascadeCategoryId;

    @ApiModelProperty(value = "名称", position = 5)
    private String name;

    @ApiModelProperty(value = "备注", position = 6)
    private String remark;

    @ApiModelProperty(value = "属性录入方式 1-手工录入;2-从列表获取", position = 7)
    private Integer inputType;

    @ApiModelProperty(value = "可选值列表 以逗号隔开", position = 8)
    private String inputList;

    @ApiModelProperty(value = "排序", position = 9)
    private Integer sort;

    @ApiModelProperty(value = "是否支持手动新增 Y-支持;N-不支持", position = 10)
    private String handAddStatus;






}
