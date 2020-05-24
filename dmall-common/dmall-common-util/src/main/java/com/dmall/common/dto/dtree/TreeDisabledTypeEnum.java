package com.dmall.common.dto.dtree;

import com.dmall.common.enums.base.CodeDescEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: TreeDisabledTypeEnum
 * @author: created by hang.yu on 2020/5/24 17:15
 */
@Getter
@AllArgsConstructor
public enum TreeDisabledTypeEnum implements CodeDescEnum<Integer> {

    /**
     * 节点全部可用
     */
    ZERO(0, "节点全部可用"),

    /**
     * 一级、二级节点不可用
     */
    ONE(1, "一级节点不可用"),

    /**
     * 属性选择分类
     */
    TWO(2, "二级节点不可用"),

    /**
     * 三级节点不可用
     */
    THREE(3, "三级节点不可用"),

    /**
     * 一级、二级节点不可用
     */
    ONE_TWO(4, "一级、二级节点不可用"),

    /**
     * 一级、三级节点不可用
     */
    ONE_THREE(5, "一级、三级节点不可用"),

    /**
     * 二级、三级节点不可
     */
    TWO_THREE(6, "二级、三级节点不可"),

    /**
     * 一级、二级、三级节点不可用
     */
    ONE_TWO_THREE(7, "一级、二级、三级节点不可用");

    private final Integer code;

    private final String desc;
}
