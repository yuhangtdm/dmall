package com.dmall.component.elasticsearch.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @description: ES搜索实体
 * @author: created by hang.yu on 2019/11/6 23:44
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ESSearch<T> {

    /**
     * 索引名称
     */
    private String indexName;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 搜索字段集合
     */
    private List<SearchField> searchFields;

    /**
     * 过滤字段集合
     */
    private List<FilterField> filterFields;

    /**
     * 范围查询
     */
    private RangeField rangeField;

    /**
     * 高亮字段
     */
    private String highLightField;

    /**
     * 分页请求实体
     */
    private ESPage esPage;

    /**
     * 排序字段
     */
    private SortField sortField;

    /**
     * 响应数据类型
     */
    private Class<T> clazz;
}
