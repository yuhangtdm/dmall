package com.dmall.pms.service.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.mapper.AttributeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: AttributeSupport
 * @author: created by hang.yu on 2020/5/30 17:16
 */
@Component
public class AttributeSupport {

    @Autowired
    private AttributeMapper attributeMapper;

    /**
     * 根据分类id和展示名称查询
     */
    public AttributeDO getByShowName(Long categoryId, String showName) {
        return attributeMapper.selectOne(Wrappers.<AttributeDO>lambdaQuery()
            .eq(AttributeDO::getCategoryId, categoryId)
            .eq(AttributeDO::getShowName, showName));
    }

}
