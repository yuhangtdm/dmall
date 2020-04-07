package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: OrderDeliverStatusEnum
 * @author: created by hang.yu on 2020/4/6 10:19
 */
@Getter
@AllArgsConstructor
public enum OrderDeliverStatusEnum implements CodeDescEnum<Integer> {

    NO(1, "未发货"),
    PART(2, "部分发货"),
    ALL(3, "全部发货");
    private Integer code;

    private String desc;
}
