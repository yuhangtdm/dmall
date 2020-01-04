package com.dmall.pms.api.dto.brand.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 品牌公共响应实体
 * @author: created by hang.yu on 2019-12-02 23:18:00
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "PageBrandResponseDTO" , description = "品牌公共响应实体" )
public class PageBrandResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "品牌id" , position = 1)
    private Long id;

    @ApiModelProperty(value = "品牌名称" , required = true, position = 2)
    private String name;

    @ApiModelProperty(value = "英文名称" , position = 3)
    private String englishName;

    @ApiModelProperty(value = "首字母" , required = true, position = 4)
    private String firstLetter;

    @ApiModelProperty(value = "品牌logo" , position = 5)
    private String logo;

    @ApiModelProperty(value = "品牌大图" , position = 6)
    private String bigPic;

    @ApiModelProperty(value = "创建人" , position = 7)
    private Long creator;

    @ApiModelProperty(value = "创建时间" , position = 9)
    private Date gmtCreated;

    @ApiModelProperty(value = "修改人" , position = 10)
    private Long modifier;

    @ApiModelProperty(value = "修改时间" , position = 12)
    private Date gmtModified;

    @ApiModelProperty(value = "状态,N-可用;Y-不可用" , position = 13)
    private String isDeleted;

}
