package com.dmall.pms.service.impl.support;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: AttributeTypeSupport
 * @author: created by hang.yu on 2019/12/31 10:37
 */
@Component
public class AttributeTypeSupport {

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    /**
     * 根据分类id查询列表
     */
    public List<AttributeTypeDO> listByCategotyId(Long categoryId) {
        return attributeTypeMapper.selectList(Wrappers.<AttributeTypeDO>lambdaQuery().eq(AttributeTypeDO::getCategoryId, categoryId));
    }

    /**
     * 根据分类id查删除
     */
    public void deleteByCategoryId(Long categoryId) {
        attributeTypeMapper.delete(Wrappers.<AttributeTypeDO>lambdaQuery().eq(AttributeTypeDO::getCategoryId, categoryId));
    }
}
