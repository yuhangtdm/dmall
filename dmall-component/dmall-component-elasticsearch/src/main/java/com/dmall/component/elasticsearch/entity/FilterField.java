package com.dmall.component.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: ES过滤实体
 * @author: created by yuhang on 2019/11/6 23:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterField {

    /**
     * 属性名称
     */
    private String fieldName;

    /**
     * 属性值
     */
    private Object fieldValue;
}
