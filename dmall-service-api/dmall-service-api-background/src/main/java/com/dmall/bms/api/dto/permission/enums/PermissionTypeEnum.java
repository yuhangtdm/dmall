package com.dmall.bms.api.dto.permission.enums;

import com.dmall.common.enums.base.KeyValueEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author: created by hang.yu on 2020/1/16 23:24
 */
@Getter
@AllArgsConstructor
public enum PermissionTypeEnum implements KeyValueEnum<Integer> {
    URI(1, "接口地址"),
    CATALOG(2, "目录"),
    MENU(3, "菜单");

    private Integer code;

    private String desc;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

}
