package com.dmall.pms.api.dto.sku.enums;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: SkuAuditStatusEnum
 * @author: created by hang.yu on 2019/12/16 17:01
 */
@Getter
@AllArgsConstructor
public enum SkuAuditStatusEnum implements KeyValueEnum<Integer> {

    NOT_AUDIT(1, "未审核" ),
    AUDIT_PASS(2, "审核通过" ),
    AUDIT_NOT_PASS(3, "审核不通过" );
    private Integer code;

    private String desc;
}
