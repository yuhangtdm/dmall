package com.dmall.oms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: OrderCommentStatusEnum
 * @author: created by hang.yu on 2020/4/6 10:19
 */
@Getter
@AllArgsConstructor
public enum OrderCommentStatusEnum implements CodeDescEnum<Integer> {

    /**
     * 未评价
     */
    NO(1, "未评价"),

    /**
     * 部分评价
     */
    PART(2, "部分评价"),

    /**
     * 全部评价
     */
    ALL(3, "全部评价");
    private final Integer code;

    private final String desc;
}
