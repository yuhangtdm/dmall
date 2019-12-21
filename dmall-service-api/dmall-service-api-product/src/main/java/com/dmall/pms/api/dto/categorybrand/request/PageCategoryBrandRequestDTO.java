package com.dmall.pms.api.dto.categorybrand.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.component.web.entity.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: 分类品牌关系分页请求实体
 * @author: created by hang.yu on 2019-12-19 20:57:17
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PageCategoryBrandRequestDTO", description =  "分类品牌关系分页请求实体")
public class PageCategoryBrandRequestDTO  extends PageRequestDTO {




    @ApiModelProperty(value = "商品分类id", position = 2)
    private Long categoryId;

    @ApiModelProperty(value = "品牌id", position = 3)
    private Long brandId;

    @ApiModelProperty(value = "排序", position = 4)
    private Integer sort;






}
