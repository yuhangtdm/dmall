package com.dmall.common.model;

import java.util.List;

/**
 * @description: 公共缓存服务
 * @author: created by hang.yu on 2019/12/29 19:19
 */
public interface CommonCacheService<T> {

    /**
     * 查询全部数据
     */
    List<T> selectAll();

    /**
     * 插入数据
     */
    int insert(T t);

    /**
     * 更新数据
     */
    T updateById(T attributeTypeDO);

    /**
     * 删除数据
     */
    int deleteById(Long id);

    /**
     * 根据id查询数据
     */
    T selectById(Long id);

}
