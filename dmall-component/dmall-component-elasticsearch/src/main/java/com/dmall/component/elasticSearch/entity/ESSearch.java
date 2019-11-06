package com.dmall.component.elasticSearch.entity;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: created by yuhang on 2019/11/6 23:44
 */
@Data
public class ESSearch {
    private String indexName;
    private String typeName;
    private  List<SearchField> searchFields;
    private List<FilterField> filterFields;
    private String hightLightField;
    private ESPage esPage;
    private SortField sortField;
    private Class<?> clazz;
}
