package com.dmall.bms.api.dto.merchants.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 商家店铺表 1期只有一家店铺公共响应实体
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonMerchantsResponseDTO", description = "商家店铺表 1期只有一家店铺公共响应实体")
public class CommonMerchantsResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", position = 1)
    private Long id;

    @ApiModelProperty(value = "商家店铺名称", position = 2)
    private String name;

    @ApiModelProperty(value = "联系人姓名", position = 3)
    private String contactName;

    @ApiModelProperty(value = "联系人电话", position = 4)
    private String contactTel;

    @ApiModelProperty(value = "创建人", position = 5)
    private Long creator;

    @ApiModelProperty(value = "创建时间", position = 6)
    private Date gmtCreated;

    @ApiModelProperty(value = "更新人", position = 7)
    private Long modifier;

    @ApiModelProperty(value = "更新时间", position = 8)
    private Date gmtModified;

    @ApiModelProperty(value = "状态 N-可用;Y-不可用", position = 9)
    private String isDeleted;


}
