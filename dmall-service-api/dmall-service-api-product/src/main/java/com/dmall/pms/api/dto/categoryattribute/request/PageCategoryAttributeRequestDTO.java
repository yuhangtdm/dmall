package com.dmall.pms.api.dto.categoryattribute.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.component.web.entity.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: 分类属性表 商品分类和商品属性的中间分页请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:17
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageCategoryAttributeRequestDTO", description =  "分类属性表 商品分类和商品属性的中间分页请求实体")
public class PageCategoryAttributeRequestDTO  extends PageRequestDTO {




    @ApiModelProperty(value = "分类id", position = 2)
    private Long categoryId;

    @ApiModelProperty(value = "分类path", position = 3)
    private String cascadeCategoryId;

    @ApiModelProperty(value = "属性id", position = 4)
    private Long attributeId;

    @ApiModelProperty(value = "是否可筛选 1-不可筛选;2-单选;3-多选", position = 5)
    private Integer canScreen;






}
