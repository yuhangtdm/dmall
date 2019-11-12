package com.dmall.component.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @description: ES搜索实体
 * @author: created by yuhang on 2019/11/6 23:44
 */
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ESSearch<T> {
    private String indexName;
    private String typeName;
    private List<SearchField> searchFields;
    private List<FilterField> filterFields;
    private String hightLightField;
    private ESPage esPage;
    private SortField sortField;
    private Class<T> clazz;
}
