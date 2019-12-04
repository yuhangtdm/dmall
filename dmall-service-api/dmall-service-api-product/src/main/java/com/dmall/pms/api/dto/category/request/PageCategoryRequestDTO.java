package com.dmall.pms.api.dto.category.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import com.dmall.component.web.entity.PageRequestDTO;
import java.util.*;
import java.math.*;

/**
 * @description: 商品分类分页请求实体
 * @author: created by hang.yu on 2019-12-02 23:54:40
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="PageCategoryRequestDTO", description="商品分类分页请求实体")
public class PageCategoryRequestDTO  extends PageRequestDTO {



    @ApiModelProperty(value = "父级id 上级分类编号", position = 2)
    private Long parentId;

    @ApiModelProperty(value = "分类名称", position = 3)
    private String name;

    @ApiModelProperty(value = "PC图标", position = 4)
    private String icon;

    @ApiModelProperty(value = "移动端图标", position = 5)
    private String mobileIcon;

    @ApiModelProperty(value = "级别 1-1级;2-2级;3-3级", position = 6)
    private Integer level;

    @ApiModelProperty(value = "描述", position = 7)
    private String description;

    @ApiModelProperty(value = "排序", position = 8)
    private Integer sort;

    @ApiModelProperty(value = "关键字 用于搜索", position = 9)
    private String keywords;

    @ApiModelProperty(value = "是否热门 Y-是;N-否", position = 10)
    private String hotStatus;

    @ApiModelProperty(value = "是否显示在导航栏 Y-是;N-否", position = 11)
    private String navStatus;

    @ApiModelProperty(value = "品牌数量", position = 12)
    private Integer brandCount;

    @ApiModelProperty(value = "属性分类数量", position = 13)
    private Integer attributeCategoryCount;

    @ApiModelProperty(value = "属性数量", position = 14)
    private Integer attributeCount;

    @ApiModelProperty(value = "路径 格式: .parentId.id.", position = 15)
    private String path;






}
