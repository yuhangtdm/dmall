package com.dmall.bms.service.impl.deliverywarehouse.enums;

import com.dmall.common.enums.error.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 商家发货仓库错误枚举
 * @author: created by hang.yu on 2020-01-05 18:36:36
 */
@Getter
@AllArgsConstructor
public enum DeliveryWarehouseErrorEnum implements ErrorCodeEnum {

    SAVE_DELIVERYWAREHOUSE_ERROR("deliveryWarehouse-001", "新增商家发货仓库失败"),
    UPDATE_DELIVERYWAREHOUSE_ERROR("deliveryWarehouse _002", "修改商家发货仓库失败"),
    DELETE_DELIVERYWAREHOUSE_ERROR("deliveryWarehouse_003", "删除商家发货仓库失败"),
    DELIVERYWAREHOUSE_NOT_EXIST("deliveryWarehouse_004", "该商家发货仓库不存在"),

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
