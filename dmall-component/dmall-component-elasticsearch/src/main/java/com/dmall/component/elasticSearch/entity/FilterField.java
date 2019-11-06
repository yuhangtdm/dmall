package com.dmall.component.elasticSearch.entity;

import lombok.Data;

/**
 * @description:
 * @author: created by yuhang on 2019/11/6 23:25
 */
@Data
public class FilterField {

    private String fieldName;

    private Object fieldValue;
}
