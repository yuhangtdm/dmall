package com.dmall.bms.api.enums;

import com.dmall.common.enums.error.ErrorCodeEnum ;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 商家发货仓库错误枚举
 * @author: created by hang.yu on 2020-04-05 16:03:43
 */
@Getter
@AllArgsConstructor
public enum DeliverWarehouseErrorEnum implements ErrorCodeEnum {

    DELIVER_WAREHOUSE_NOT_EXIST("1000","该商家发货仓库不存在"),

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
