package com.dmall.common.model;

import cn.hutool.core.util.ObjectUtil;
import com.dmall.common.dto.dtree.DTreeResponseDTO;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: DTreeUtil
 * @author: created by hang.yu on 2020/5/24 16:47
 */
public class DTreeUtil {

    public static List<DTreeResponseDTO> build(List<DTreeResponseDTO> treeList) {
        return build(treeList, 0L);
    }

    /**
     * 构建树数据
     */
    public static List<DTreeResponseDTO> build(List<DTreeResponseDTO> treeList, Long parentId) {
        Map<Long, DTreeResponseDTO> treeMap = treeList.stream()
            .collect(Collectors.toMap(DTreeResponseDTO::getId, responseDTO -> responseDTO));
        List<DTreeResponseDTO> tree = Lists.newArrayList();
        // 添加自身以及子节点
        treeMap.forEach((k, v) -> {
            // 默认一级树的父id为0
            if (parentId == 0L && ObjectUtil.equal(v.getParentId(), parentId)) {
                tree.add(v);
            }
            // 添加自身
            if (parentId != 0L && ObjectUtil.equal(k, parentId)) {
                tree.add(v);
            }
        });
        // 遍历所有元素
        treeMap.forEach((k, v) -> {
            if (treeMap.get(v.getParentId()) != null) {
                treeMap.get(v.getParentId()).getChildren().add(v);
            }
        });
        return tree;
    }
}
