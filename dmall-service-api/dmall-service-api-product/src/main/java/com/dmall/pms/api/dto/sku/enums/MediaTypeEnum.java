package com.dmall.pms.api.dto.sku.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/17 12:35
 */
@Getter
@AllArgsConstructor
public enum MediaTypeEnum implements CodeDescEnum<Integer> {

    PIC(1, "图片"),
    VIDEO(2, "视频"),
    ;

    private Integer code;

    private String desc;
}
