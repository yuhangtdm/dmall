package com.dmall.bms.api.dto.merchants.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 商家店铺表 1期只有一家店铺列表请求实体
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListMerchantsRequestDTO", description = "商家店铺表 1期只有一家店铺列表请求实体")
public class ListMerchantsRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "商家店铺名称", position = 2)
    private String name;

    @ApiModelProperty(value = "联系人姓名", position = 3)
    private String contactName;

    @ApiModelProperty(value = "联系人电话", position = 4)
    private String contactTel;


}
