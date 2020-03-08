package com.dmall.component.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: created by hang.yu on 2020/3/8 14:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RangeField {
    /**
     * 属性名称
     */
    private String fieldName;

    /**
     * 起始值
     */
    private Object startValue;

    /**
     * 结束值
     */
    private Object endValue;
}
