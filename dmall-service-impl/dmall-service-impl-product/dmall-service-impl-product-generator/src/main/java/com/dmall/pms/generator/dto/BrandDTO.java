package com.dmall.pms.generator.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 品牌表
 * @author: created by hang.yu on 2019-12-19 20:57:17
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "BrandDTO", description = "品牌表")
public class BrandDTO implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "名称", position = 2)
    private String name;

    @ApiModelProperty(value = "英文名称", position = 3)
    private String englishName;

    @ApiModelProperty(value = "首字母", position = 4)
    private String firstLetter;

    @ApiModelProperty(value = "logo", position = 5)
    private String logo;

    @ApiModelProperty(value = "品牌大图", position = 6)
    private String bigPic;

    @ApiModelProperty(value = "创建人", position = 7)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 8)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 9)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 10)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 11)
    private String isDeleted;

}
