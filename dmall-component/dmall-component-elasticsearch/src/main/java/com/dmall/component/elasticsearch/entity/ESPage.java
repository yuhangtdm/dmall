package com.dmall.component.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: ES分页实体
 * @author: created by hang.yu on 2019/11/6 23:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ESPage{

    /**
     * 查询的起始索引
     */
    private Integer from;

    /**
     * 查询的大小
     */
    private Integer size;


}
