package com.dmall.bms.api.dto.deliverywarehouse.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.*;
import java.math.*;
import java.io.Serializable;

/**
 * @description: 商家发货仓库列表请求实体
 * @author: created by hang.yu on 2020-01-13 23:04:02
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListDeliveryWarehouseRequestDTO", description = "商家发货仓库列表请求实体")
public class ListDeliveryWarehouseRequestDTO implements Serializable {

    private static final long serialVersionUID=1L;


        @ApiModelProperty(value = "商家店铺id", position = 2)
        private Long merchantsId;

        @ApiModelProperty(value = "仓库名称", position = 3)
        private String name;

        @ApiModelProperty(value = "仓库所在省/直辖市", position = 4)
        private String province;

        @ApiModelProperty(value = "仓库所在市", position = 5)
        private String city;

        @ApiModelProperty(value = "仓库所在区", position = 6)
        private String region;

        @ApiModelProperty(value = "仓库详细地址", position = 7)
        private String detailAddress;

        @ApiModelProperty(value = "仓库联系人姓名", position = 8)
        private String contactName;

        @ApiModelProperty(value = "仓库联系人电话", position = 9)
        private String contactTel;

        @ApiModelProperty(value = "默认发货地址 Y-是,N-否", position = 10)
        private String deliveryStatus;

        @ApiModelProperty(value = "默认收货地址 Y-是,N-否", position = 11)
        private String receiveStatus;







}
