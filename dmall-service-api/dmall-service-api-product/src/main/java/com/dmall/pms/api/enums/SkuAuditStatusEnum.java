package com.dmall.pms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: SkuAuditStatusEnum
 * @author: created by hang.yu on 2019/12/16 17:01
 */
@Getter
@AllArgsConstructor
public enum SkuAuditStatusEnum implements CodeDescEnum<Integer> {

    /**
     * 未审核
     */
    NOT_AUDIT(1, "未审核"),

    /**
     * 审核通过
     */
    AUDIT_PASS(2, "审核通过"),

    /**
     * 审核不通过
     */
    AUDIT_NOT_PASS(3, "审核不通过");

    private final Integer code;

    private final String desc;
}
