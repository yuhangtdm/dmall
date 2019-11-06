package com.dmall.component.elasticSearch.entity;

import lombok.Data;
import org.elasticsearch.search.sort.SortOrder;

/**
 * @description:
 * @author: created by yuhang on 2019/11/6 23:31
 */
@Data
public class SortField {

    private String fieldName;

    private SortOrder sortOrder;
}
