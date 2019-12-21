package com.dmall.pms.service.impl.attributetype.wrapper;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;

/**
 * @description: LambdaQueryWrapperBuilder
 * @author: created by hang.yu on 2019/12/5 20:50
 */
public class LambdaQueryWrapperBuilder {

    public static LambdaQueryWrapper queryWrapper(Long categoryId, String name, String showName, Integer type) {
        LambdaQueryWrapper<AttributeTypeDO> queryWrapper = Wrappers.<AttributeTypeDO>lambdaQuery()
                .eq(categoryId != null, AttributeTypeDO::getCategoryId, categoryId)
//                .eq(type != null, AttributeTypeDO::getType, type)
                .like(StrUtil.isNotBlank(name), AttributeTypeDO::getName, name)
                .like(StrUtil.isNotBlank(showName), AttributeTypeDO::getShowName, showName)
                .orderByAsc(AttributeTypeDO::getSort);
        return queryWrapper;
    }

}
