package com.dmall.pms;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.mapper.AttributeTypeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/7 22:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductServiceApp.class)
public class TestUpdate {
    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Test
    public void test(){
        AttributeTypeDO attributeTypeDO = new AttributeTypeDO();
        attributeTypeDO.setId(1L);
        attributeTypeDO.setCategoryId(2L);
        LambdaUpdateWrapper<AttributeTypeDO> updateWrapper = Wrappers.<AttributeTypeDO>update().lambda()
                .eq(AttributeTypeDO::getId, 1L)
                .set(AttributeTypeDO::getCategoryId, null)
                .set(AttributeTypeDO::getCascadeCategoryId, null);
//        attributeTypeMapper.update(null, updateWrapper);

        attributeTypeMapper.updateById(attributeTypeDO);
    }
}
