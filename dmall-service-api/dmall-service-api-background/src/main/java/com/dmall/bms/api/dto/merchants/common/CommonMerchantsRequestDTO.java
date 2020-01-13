package com.dmall.bms.api.dto.merchants.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.*;
import java.math.*;

/**
 * @description: 商家店铺表 1期只有一家店铺公共请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:03
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "CommonMerchantsRequestDTO", description = "商家店铺表 1期只有一家店铺公共请求实体")
public class CommonMerchantsRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "商家店铺名称", position = 2)
    private String name;

    @ApiModelProperty(value = "联系人姓名", position = 3)
    private String contactName;

    @ApiModelProperty(value = "联系人电话", position = 4)
    private String contactTel;







}
