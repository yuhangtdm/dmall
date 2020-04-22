package com.dmall.pms.api.enums;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 媒体类型枚举
 * @author: created by hang.yu on 2019/12/17 12:35
 */
@Getter
@AllArgsConstructor
public enum MediaTypeEnum implements CodeDescEnum<Integer> {

    /**
     * 图片
     */
    PIC(1, "图片"),

    /**
     * 视频
     */
    VIDEO(2, "视频"),
    ;

    private final Integer code;

    private final String desc;
}
