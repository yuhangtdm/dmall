package com.dmall.bms.service.impl.deliverywarehouse.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 商家发货仓库错误枚举
 * @author: created by hang.yu on 2020-01-13 23:04:02
 */
@Getter
@AllArgsConstructor
public enum DeliveryWarehouseErrorEnum implements ErrorCodeEnum {

    DELIVERYWAREHOUSE_NOT_EXIST("deliveryWarehouse_100","该商家发货仓库不存在"),

    ;

   /**
    * 错误码
    */
    private String code;

    /**
     * 错误描述
     */
    private String msg;

}
