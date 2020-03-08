package com.dmall.search.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 搜索返回实体
 * @author: created by hang.yu on 2020/3/5 23:23
 */
@Data
@ApiModel(value = "SearchRequestDTO", description = "搜索平台请求实体")
public class SearchRequestDTO implements Serializable {

    @ApiModelProperty(value = "分类id", position = 1)
    private Long cat;

    @ApiModelProperty(value = "品牌id,多个用逗号隔开", position = 2)
    private String bra;

    @ApiModelProperty(value = "搜索关键字", position = 3)
    private String keyword;

    /**
     * 属性id_属性值id,属性值id:%属性id_属性值id
     */
    @ApiModelProperty(value = "选中的属性值,属性值以,隔开", position = 4)
    private String ev;

    @ApiModelProperty(value = "排序", position = 6)
    private String sort;

    @ApiModelProperty(value = "当前页", position = 7)
    private Integer from = 0;

    @ApiModelProperty(value = "每页记录数", position = 9)
    private Integer size = 20;

    @ApiModelProperty(value = "价格起始值", position = 10)
    private BigDecimal priceStart;

    @ApiModelProperty(value = "价格结束值", position = 11)
    private BigDecimal priceEnd;

}
