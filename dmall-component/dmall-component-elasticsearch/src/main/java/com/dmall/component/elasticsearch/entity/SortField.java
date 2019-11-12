package com.dmall.component.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.search.sort.SortOrder;

/**
 * @description: ES排序实体
 * @author: created by yuhang on 2019/11/6 23:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortField {

    /**
     * 属性名称
     */
    private String fieldName;

    /**
     * 排序方式
     */
    private SortOrder sortOrder;
}
